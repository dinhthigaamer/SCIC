export default function Avatar({ src }) {
  return (
    <div className="w-20 h-20 rounded-full overflow-hidden bg-gray-200 text-center flex justify-center">
      <img src={src} alt="Avatar" className="w-full h-full object-cover" />
    </div>
  );
}
