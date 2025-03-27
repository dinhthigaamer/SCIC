import { useState, useEffect } from "react";

export default function RegisterForm() {
  const [formData, setFormData] = useState({
    user_name: "",
    password: "",
    age: "",
    gender: "",
    marital_status: "",
    emotion: "",
  });

  const [submitted, setSubmitted] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  // Xử lý nhập liệu
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Xử lý Submit
  const handleSubmit = (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    // Kiểm tra nhập liệu
    if (!formData.user_name || !formData.password || !formData.age || !formData.gender || !formData.marital_status || !formData.emotion) {
      setError("Vui lòng nhập đầy đủ thông tin!");
      return;
    }

    setSubmitted(true);
    setLoading(true);
  };

  // useEffect gọi API khi submitted
  useEffect(() => {
    if (submitted) {
      fetch("https://example.com/api/add_user", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      })
        .then((res) => res.json())
        .then((data) => {
          setSuccess("Đăng ký thành công! 🎉");
          setFormData({ user_name: "", password: "", age: "", gender: "", marital_status: "", emotion: "" });
        })
        .catch(() => setError("Lỗi khi đăng ký, vui lòng thử lại!"))
        .finally(() => {
          setSubmitted(false);
          setLoading(false);
        });
    }
  }, [submitted]);

  return (
    <div className="max-w-md mx-auto mt-8 p-6 bg-white shadow-lg rounded-lg">
      <h2 className="text-2xl font-semibold text-center text-blue-600 mb-4">Đăng ký tài khoản</h2>

      {error && <p className="text-red-500 text-center mb-2">{error}</p>}
      {success && <p className="text-green-500 text-center mb-2">{success}</p>}

      <form onSubmit={handleSubmit} className="flex flex-col gap-4">
        <input
          type="text"
          name="user_name"
          placeholder="Tên đăng nhập"
          value={formData.user_name}
          onChange={handleChange}
          className="p-2 border rounded-md focus:ring-2 focus:ring-blue-500"
          required
        />

        <input
          type="password"
          name="password"
          placeholder="Mật khẩu"
          value={formData.password}
          onChange={handleChange}
          className="p-2 border rounded-md focus:ring-2 focus:ring-blue-500"
          required
        />

        <input
          type="number"
          name="age"
          placeholder="Tuổi"
          value={formData.age}
          onChange={handleChange}
          className="p-2 border rounded-md focus:ring-2 focus:ring-blue-500"
          required
        />

        <select name="gender" value={formData.gender} onChange={handleChange} className="p-2 border rounded-md focus:ring-2 focus:ring-blue-500" required>
          <option value="">Chọn giới tính</option>
          <option value="male">Nam</option>
          <option value="female">Nữ</option>
          <option value="other">Khác</option>
        </select>

        <select name="marital_status" value={formData.marital_status} onChange={handleChange} className="p-2 border rounded-md focus:ring-2 focus:ring-blue-500" required>
          <option value="">Chọn tình trạng hôn nhân</option>
          <option value="single">Độc thân</option>
          <option value="married">Đã kết hôn</option>
        </select>

        <input
          type="text"
          name="emotion"
          placeholder="Cảm xúc hiện tại"
          value={formData.emotion}
          onChange={handleChange}
          className="p-2 border rounded-md focus:ring-2 focus:ring-blue-500"
          required
        />

        <button
          type="submit"
          className="bg-blue-600 text-white p-2 rounded-md hover:bg-blue-700 transition disabled:opacity-50"
          disabled={loading}
        >
          {loading ? "Đang xử lý..." : "Đăng ký"}
        </button>
      </form>
    </div>
  );
}
