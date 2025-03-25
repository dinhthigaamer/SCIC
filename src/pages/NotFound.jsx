import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="flex flex-col items-center justify-center h-screen text-center">
      <h1 className="text-6xl font-bold text-gray-700">404</h1>
      <p className="text-xl mt-2">Oops! Trang bạn tìm không tồn tại.</p>
      <Link
        to="/"
        className="mt-4 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-700"
      >
        Quay về Trang chủ
      </Link>
    </div>
  );
}
