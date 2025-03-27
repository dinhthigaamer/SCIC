// import { useLocation, Link } from "react-router-dom";

// export default function Navbar() {
//   const location = useLocation();

//   const navItems = [
//     { name: "Trang chủ", path: "/" },
//     { name: "Trò chuyện", path: "/chat" },
//     { name: "Nhật ký cảm xúc", path: "/journal" },
//     { name: "Thư viện cảm xúc", path: "/library" },
//   ];

//   return (
//     <nav className="bg-white shadow-md p-4 flex justify-between items-center">
//       <h1 className="text-xl font-bold text-blue-600">Counselor</h1>

//       {/* Thanh menu */}
//       <div className="flex gap-6">
//         {navItems.map((item) => (
//           <Link
//             key={item.path}
//             to={item.path}
//             className={`px-4 py-2 rounded-md transition-colors ${
//               location.pathname === item.path
//                 ? "text-blue-600 font-semibold border-b-2 border-blue-600"
//                 : "text-gray-700 hover:text-blue-500"
//             }`}
//           >
//             {item.name}
//           </Link>
//         ))}
//       </div>

//       {/* Nút Đăng nhập & Đăng ký */}
//       <div className="flex gap-4">
//         <Link
//           to="/login"
//           className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
//         >
//           Đăng nhập
//         </Link>
//         <Link
//           to="/register"
//           className="px-4 py-2 border border-blue-600 text-blue-600 rounded-lg hover:bg-blue-600 hover:text-white transition"
//         >
//           Đăng ký
//         </Link>
//       </div>
//     </nav>
//   );
// }

import { useLocation, Link } from "react-router-dom";
import { useState } from "react";
import { FaUserCircle } from "react-icons/fa"; // Icon user

export default function Navbar() {
  const location = useLocation();
  const [isOpen, setIsOpen] = useState(false); // Trạng thái mở menu

  const navItems = [
    { name: "Trang chủ", path: "/" },
    { name: "Trò chuyện", path: "/chat" },
    { name: "Nhật ký cảm xúc", path: "/journal" },
    { name: "Thư viện cảm xúc", path: "/library" },
  ];

  return (
    <nav className="bg-white shadow-md p-4 flex justify-between items-center relative">
      <h1 className="text-xl font-bold text-blue-600">Counselor</h1>

      {/* Thanh menu */}
      <div className="flex gap-6">
        {navItems.map((item) => (
          <Link
            key={item.path}
            to={item.path}
            className={`px-4 py-2 rounded-md transition-colors ${
              location.pathname === item.path
                ? "text-blue-600 font-semibold border-b-2 border-blue-600"
                : "text-gray-700 hover:text-blue-500"
            }`}
          >
            {item.name}
          </Link>
        ))}
      </div>

      {/* Avatar + Dropdown menu */}
      <div className="relative">
        <FaUserCircle
          className="text-3xl text-gray-600 cursor-pointer hover:text-blue-600"
          onClick={() => setIsOpen(!isOpen)}
        />
        {isOpen && (
          <div className="absolute right-0 mt-2 w-40 bg-white shadow-lg rounded-lg py-2 z-10">
            <Link to="/profile" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">
              Hồ sơ
            </Link>
            <Link to="/settings" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">
              Cài đặt
            </Link>
            <button className="block w-full text-left px-4 py-2 text-red-600 hover:bg-gray-100">
              Đăng xuất
            </button>
          </div>
        )}
      </div>
    </nav>
  );
}
