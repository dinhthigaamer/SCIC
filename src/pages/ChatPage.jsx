import { useState, useRef, useEffect, useLayoutEffect } from "react";
import ChatMessage from "../components/ChatMessage";
import ChatInput from "../components/ChatInput";

export default function Chat() {
  const API_URL = import.meta.env.VITE_API_URL;

  const [messages, setMessages] = useState([
    { text: "Xin chào! Tôi có thể giúp gì cho bạn?", isUser: false, isSuicide: true },
  ]);
  
  const chatContainerRef = useRef(null); // Tạo ref để theo dõi khung chat
  const [isTyping, setIsTyping] = useState(false);
  const [Id, setId] = useState(null);
  const [page, setPage] = useState(1);
  const [hasMore, setHasMore] = useState(true);

  useEffect(() => {
    fetch("/config.json") // Đọc file JSON
      .then((response) => response.json())
      .then((data) => setId(data._id))
      .catch((error) => console.error("Lỗi khi đọc file JSON:", error));
  });

  // Load lịch sử tin nhắn trang đầu tiên
  useEffect(() => {
    fetch("/config.json") // Đọc file JSON
      .then((response) => response.json())
      .then((data) => {
        setId(data._id);
        loadMessages(data._id, 0); // Tải trang đầu tiên
      })
      .catch((error) => console.error("Lỗi khi đọc file JSON:", error));
  });

  const loadMessages = async (userId, pageNumber) => {
    console.log("Đã load")
    if (userId === null) return;

    try {
      const response = await fetch(`/api/history/${userId}/${pageNumber}`,{
        method: "GET",
        headers: { "Content-Type": "application/json" },
      });
      console.log(`/api/history/${userId}/${pageNumber}`);
      const data = await response.json();

      if (data.length === 0) {
        setHasMore(false); // Không còn tin nhắn cũ để tải
        return;
      }

      setMessages((prevMessages) => [...data, ...prevMessages]);
    } catch (error) {
      console.error("Lỗi khi tải tin nhắn cũ:", error);
    }
  };

  const handleSendMessage = async (text) => {
    if (!text.trim()) return;

    // Thêm tin nhắn của người dùng vào giao diện
    const newMessages = [...messages, { text, isUser: true,isSuicide: false }];
    setMessages(newMessages);
    setIsTyping(true);
  
    try {
      // Gửi tin nhắn đến API backend
      console.log(`/api/chat/${Id}`)
      const response = await fetch(`/api/chat/${Id}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: text }),
      });
      
      const data = await response.json();
      console.log("Response:", data.response);

      const formattedMessage = data.response.replace(/\n/g, "\r\n");
      // Thêm phản hồi từ bot vào danh sách tin nhắn
      setMessages([...newMessages, { text: formattedMessage, isUser: false, isSuicide: data.isSuicide }]);
      setPage(0);
    } catch (error) {
      console.error("Lỗi khi gọi API:", error);
      setMessages([
        ...newMessages,
        { text: "Xin lỗi, có lỗi xảy ra!", isUser: false },
      ]);
    } finally {
      setIsTyping(false);
    }
  };

  // Auto-scroll xuống tin nhắn mới nhất
  useLayoutEffect(() => {
    if (!isTyping || chatContainerRef.current) {
      chatContainerRef.current.scrollTop = chatContainerRef.current.scrollHeight;
    }
  }, [messages, isTyping]); // Chạy khi tin nhắn mới đến hoặc bot hoàn thành

  const handleScroll = () => {
    if (!chatContainerRef.current || !hasMore) return;

    if (chatContainerRef.current.scrollTop === 0) {
      setPage((prevPage) => prevPage + 1);
      loadMessages(page + 1, Id);
    }
  };

  return (
    <div ref={chatContainerRef} className="flex flex-col min-h-screen w-[60%] mx-auto" onScroll={handleScroll}>
      <div className="flex-1 p-6 overflow-auto">
        {messages.map((msg, index) => (
          <ChatMessage key={index} message={msg.text} isUser={msg.isUser} isSuicide={msg.isSuicide}/>
        ))}
        {isTyping && (
          <ChatMessage isTyping={true} isUser={false} isSuicide={true} />
        )}
      </div>
      <ChatInput onSendMessage={handleSendMessage} />
    </div>
  );
}
