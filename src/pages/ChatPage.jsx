import { useState, useRef, useEffect, useLayoutEffect } from "react";
import ChatMessage from "../components/ChatMessage";
import ChatInput from "../components/ChatInput";
import axios from "axios";

export default function Chat() {
  // const publicUrl = "https://7c74-34-173-117-158.ngrok-free.app";
  const chatContainerRef = useRef(null);
  const [messages, setMessages] = useState([
    { text: "Xin chào! Tôi có thể giúp gì cho bạn?", isUser: false, isSuicide: true },
  ]);
  const [isTyping, setIsTyping] = useState(false);
  const [Id, setId] = useState(null);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);

  useEffect(() => {
    fetch("/config.json")
      .then((response) => response.json())
      .then((data) => {
        console.log("Config data:", data);
        setId(data._id);
      })
      .catch((error) => console.error("Lỗi khi đọc file JSON:", error));
  }, []);

  useEffect(() => {
    if (Id) {
      console.log("Tải trang đầu tiên với Id:", Id);
      loadMessages(Id, 0);
    }
  }, [Id]);

  const loadMessages = async (userId, pageNumber) => {
    console.log("Đã load, userId:", userId, "pageNumber:", pageNumber);
    if (!userId) return;

    const apiUrl = `/api/history/${userId}/${pageNumber}`;
    console.log("API URL:", apiUrl);

    try {
      console.log("Đang gửi request...");
      const response = await axios.get(apiUrl, {
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json",
          "Cache-Control": "no-cache",
        },
      });
      console.log("Phản hồi từ API:", response.data);
      const data = response.data;

      if (!Array.isArray(data)) {
        console.error("Dữ liệu không phải mảng:", data);
        throw new Error("Dữ liệu không phải mảng!");
      }

      if (data.length === 0) {
        setHasMore(false);
        return;
      }

      setMessages((prevMessages) => [
        ...data.map((item) => ({
          text: item.content,
          isUser: item.role === "user",
          isSuicide: false,
        })),
        ...prevMessages,
      ]);
    } catch (error) {
      console.error("Lỗi khi gọi API:", error.message);
      if (error.response) {
        console.log("Status:", error.response.status);
        console.log("Data:", error.response.data);
      } else if (error.request) {
        console.log("Không nhận được phản hồi từ server");
      } else {
        console.log("Lỗi khác:", error);
      }
    }
  };

  const handleSendMessage = async (text) => {
    if (!text.trim()) return;

    const newMessages = [...messages, { text, isUser: true, isSuicide: false }];
    setMessages(newMessages);
    setIsTyping(true);

    try {
      const response = await fetch(`/api/chat/${Id}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: text }),
      });
      const data = await response.json();
      console.log("Response:", data.response);

      const formattedMessage = data.response.replace(/\n/g, "\r\n");
      setMessages([...newMessages, { text: formattedMessage, isUser: false, isSuicide: data.isSuicide }]);
      setPage(0);
    } catch (error) {
      console.error("Lỗi khi gọi API:", error);
      setMessages([...newMessages, { text: "Xin lỗi, có lỗi xảy ra!", isUser: false }]);
    } finally {
      setIsTyping(false);
    }
  };

  useLayoutEffect(() => {
    if (!isTyping && chatContainerRef.current) {
      chatContainerRef.current.scrollTop = chatContainerRef.current.scrollHeight;
    }
  }, [messages, isTyping]);

  const handleScroll = () => {
    if (!chatContainerRef.current || !hasMore) return;
    if (chatContainerRef.current.scrollTop === 0) {
      setPage((prevPage) => {
        const newPage = prevPage + 1;
        console.log("Scroll: Tải trang", newPage);
        loadMessages(Id, newPage);
        return newPage;
      });
    }
  };

  return (
    <div ref={chatContainerRef} className="flex flex-col min-h-screen w-[60%] mx-auto" onScroll={handleScroll}>
      <div className="flex-1 p-6 overflow-auto">
        {messages.map((msg, index) => (
          <ChatMessage key={index} message={msg.text} isUser={msg.isUser} isSuicide={msg.isSuicide} />
        ))}
        {isTyping && <ChatMessage isTyping={true} isUser={false} isSuicide={true} />}
      </div>
      <ChatInput onSendMessage={handleSendMessage} />
    </div>
  );
}