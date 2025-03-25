import { useState } from "react";

export default function ChatInput({ onSendMessage }) {
  const [input, setInput] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (input.trim()) {
      onSendMessage(input);
      setInput(""); // Xóa nội dung input sau khi gửi
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="flex items-center border-none rounded-full sticky bottom-0 w-full gap-2"
    >
      <input
        type="text"
        className="flex-1 p-2 outline-none rounded-full"
        placeholder="Nhập tin nhắn..."
        value={input}
        onChange={(e) => setInput(e.target.value)}
      />
      <button
        type="submit"
        className="bg-blue-500 text-white px-4 py-2 rounded-lg"
      >
        Gửi
      </button>
    </form>
  );
}
