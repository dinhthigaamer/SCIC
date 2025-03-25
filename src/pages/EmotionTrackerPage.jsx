import { useState } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
  Cell,
} from "recharts";
import { FaSmile, FaMeh, FaFrown, FaAngry } from "react-icons/fa";
import { ImHappy2 } from "react-icons/im";
import ChatMessage from "../components/ChatMessage";

const emotions = [
  { date: "01/02", value: 1 },
  { date: "02/02", value: 2 },
  { date: "03/02", value: 3 },
  { date: "04/02", value: 4 },
  { date: "05/02", value: 5 },
  { date: "06/02", value: 3 },
  { date: "07/02", value: 4 },
];

const getColor = (value) => {
  switch (value) {
    case 1:
      return "#ff4d4d"; // Đỏ (Buồn)
    case 2:
      return "#ff9800"; // Cam (Khá buồn)
    case 3:
      return "#ffc107"; // Vàng (Bình thường)
    case 4:
      return "#86efac"; // Xanh lá (Vui)
    case 5:
      return "#16a34a"; // Xanh dương (Rất vui)
    default:
      return "#d9d9d9"; // Xám (Không xác định)
  }
};

const EmotionTracker = () => {
  const [selectedRange, setSelectedRange] = useState("01/02 - 07/02");

  return (
    <div className="w-[50%] m-auto">
      <ChatMessage message="Đây là nhật ký theo dõi của bạn nhé!" />
      <div className="p-10 bg-white rounded-xl shadow-md m-auto mt-10 bg-gradient-to-b from-[#E2FCFB] to-[#FFFFFF]">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold">Nhật ký theo dõi cảm xúc</h2>
          <span className="text-gray-500">{selectedRange} ▼</span>
        </div>
        <div className="flex gap-4">
          <div className="relative flex flex-col justify-end items-center ml-2">
            <div className="absolute bottom-0 h-[550px] flex items-center">
              <ImHappy2 className="text-green-600 text-4xl" />
            </div>
            <div className="absolute bottom-0 h-[450px] flex items-center">
              <FaSmile className="text-green-400 text-4xl" />
            </div>
            <div className="absolute bottom-0 h-[350px] flex items-center">
              <FaMeh className="text-yellow-500 text-4xl" />
            </div>
            <div className="absolute bottom-0 h-[250px] flex items-center">
              <FaFrown className="text-orange-500 text-4xl" />
            </div>
            <div className="absolute bottom-0 h-[150px] flex items-center">
              <FaAngry className="text-red-500 text-4xl" />
            </div>
          </div>

          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={emotions}>
              <XAxis dataKey="date" />
              <YAxis hide domain={[0, 5]} ticks={[1, 2, 3, 4, 5]} />
              <Tooltip />
              <Bar dataKey="value" radius={[10, 10, 0, 0]} barSize={40}>
                {emotions.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={getColor(entry.value)} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  );
};

export default EmotionTracker;
