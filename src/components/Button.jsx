import { Link } from "react-router-dom";

export default function Button({ children, to, className = "", ...props }) {
  const baseClass = "px-4 py-2 rounded-lg text-white font-bold transition";

  if (to) {
    // Nếu có prop `to`, sử dụng <Link> thay vì <button>
    return (
      <Link to={to} className={`${baseClass} ${className}`} {...props}>
        {children}
      </Link>
    );
  }

  return (
    <button className={`${baseClass} ${className}`} {...props}>
      {children}
    </button>
  );
}
