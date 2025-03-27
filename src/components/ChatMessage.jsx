import userAvatar from "../assets/chat-icon.png";
import botAvatar from "../assets/chat-icon.png";
import Button from "./Button";
import { useNavigate } from "react-router-dom";

export default function ChatMessage({
  message,
  isUser,
  isTyping,
  isSuicide,
  onConfirm,
}) {
  const navigate = useNavigate();
  console.log(isSuicide);
  return (
    <div
      className={`flex items-end ${
        isUser ? "justify-end" : "justify-start"
      } m-4`}
    >
      {!isUser && (
        <img
          src={botAvatar}
          alt="Bot"
          className="w-12 h-12 rounded-full mr-2"
        />
      )}
      <div
        className={`p-3 rounded-2xl max-w-[75%] text-justify ${
          isUser ? "bg-blue-500 text-white" : "bg-gray-200 text-black"
        }`}
        style={{ whiteSpace: "pre-line" }} // Tự động xử lý xuống dòng
      >
        {isTyping ? (
          <div className="flex space-x-1">
            <span className="w-2 h-2 bg-gray-500 rounded-full animate-bounce"></span>
            <span className="w-2 h-2 bg-gray-500 rounded-full animate-bounce delay-150"></span>
            <span className="w-2 h-2 bg-gray-500 rounded-full animate-bounce delay-300"></span>
          </div>
        ) : (
          <>
            {message}
            {isSuicide && (
              <div className="mt-2">
                <Button
                  className="bg-red-500 hover:bg-red-700 w-full"
                  onClick={() => {
                    // onConfirm();
                    navigate("/CounselorList"); // ✅ Điều hướng đến trang
                  }}
                >
                  Đồng ý
                </Button>
              </div>
            )}
          </>
        )}
      </div>
      {isUser && (
        <img
          src={userAvatar}
          alt="User"
          className="w-12 h-12 rounded-full ml-2"
        />
      )}
    </div>
  );
}
