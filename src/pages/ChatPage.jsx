import { useState, useRef, useEffect } from "react";
import ChatMessage from "../components/ChatMessage";
import ChatInput from "../components/ChatInput";

export default function Chat() {
  const [messages, setMessages] = useState([
    { text: "Xin chào! Tôi có thể giúp gì cho bạn?", isUser: false },
  ]);
  const [isTyping, setIsTyping] = useState(false);
  const chatContainerRef = useRef(null); // Tạo ref để theo dõi khung chat

  const handleSendMessage = (text) => {
    if (!text.trim()) return;

    setMessages((prev) => [...prev, { text, isUser: true }]);
    setIsTyping(true);

    setTimeout(() => {
      let botResponse = "Mình đang suy nghĩ... 🤔";
      let isSuicide = false;

      if (
        text.toLowerCase().includes("buồn") ||
        text.toLowerCase().includes("chán nản")
      ) {
        botResponse =
          "Mình cảm thấy bạn đang không ổn. Bạn có muốn nhận sự giúp đỡ không?";
        isSuicide = true;
      } else {
        botResponse = "Mình hiểu, bạn có thể chia sẻ thêm với mình nhé! 😊";
      }

      setMessages((prev) => [
        ...prev,
        { text: botResponse, isUser: false, isSuicide },
      ]);
      setIsTyping(false);
    }, 2000);
  };

  // Auto-scroll xuống tin nhắn mới nhất
  useEffect(() => {
    if (chatContainerRef.current) {
      chatContainerRef.current.scrollTop =
        chatContainerRef.current.scrollHeight;
    }
  }, [messages]);

  // const handleSendMessage = async (text) => {
  //   if (!text.trim()) return;

  //   // Thêm tin nhắn của người dùng vào giao diện
  //   const newMessages = [...messages, { text, isUser: true }];
  //   setMessages(newMessages);
  //   setIsTyping(true);

  //   try {
  //     // Gửi tin nhắn đến API backend
  //     const response = await fetch("https://your-api-url.com/chat", {
  //       method: "POST",
  //       headers: { "Content-Type": "application/json" },
  //       body: JSON.stringify({ message: text }),
  //     });

  //     const data = await response.json();

  //     // Thêm phản hồi từ bot vào danh sách tin nhắn
  //     setMessages([...newMessages, { text: data.reply, isUser: false }]);
  //   } catch (error) {
  //     console.error("Lỗi khi gọi API:", error);
  //     setMessages([
  //       ...newMessages,
  //       { text: "Xin lỗi, có lỗi xảy ra!", isUser: false },
  //     ]);
  //   } finally {
  //     setIsTyping(false);
  //   }
  // };

  return (
    <div className="flex flex-col min-h-screen w-[60%] mx-auto">
      <div className="flex-1 p-6 overflow-auto">
        {messages.map((msg, index) => (
          <ChatMessage key={index} message={msg.text} isUser={msg.isUser} />
        ))}
        {isTyping && (
          <ChatMessage isTyping={true} isUser={false} isSuicide={true} />
        )}
      </div>
      <ChatInput onSendMessage={handleSendMessage} />
    </div>
  );
}
