import { useLocation, Link } from "react-router-dom";

export default function Navbar() {
  const location = useLocation();

  const navItems = [
    { name: "Trang chủ", path: "/" },
    { name: "Trò chuyện", path: "/chat" },
    { name: "Nhật ký cảm xúc", path: "/journal" },
    { name: "Thư viện cảm xúc", path: "/library" },
  ];

  return (
    <nav className="bg-white shadow-md p-4 flex justify-between items-center">
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

      {/* Nút Đăng nhập */}
      <Link
        to="/login"
        className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
      >
        Đăng nhập
      </Link>
    </nav>
  );
}
