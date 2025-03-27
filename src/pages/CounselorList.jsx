export default function CounselorList() {
    const counselors = [
      {
        name: "Chuyên gia tâm lý X",
        phone: "0981872467",
        address: "Hà Nội",
        image: "/image1.png", // Đổi thành đường dẫn hình ảnh phù hợp
      },
      {
        name: "Chuyên gia tâm lý X",
        phone: "0981872467",
        address: "Hà Nội",
        image: "/image1.png", // Đổi thành đường dẫn hình ảnh phù hợp
      },
      {
        name: "Chuyên gia tâm lý X",
        phone: "0981872467",
        address: "Hà Nội",
        image: "/image1.png", // Đổi thành đường dẫn hình ảnh phù hợp
      },
      {
        name: "Chuyên gia tâm lý X",
        phone: "0981872467",
        address: "Hà Nội",
        image: "/image1.png", // Đổi thành đường dẫn hình ảnh phù hợp
      },
      {
        name: "Chuyên gia tâm lý X",
        phone: "0981872467",
        address: "Hà Nội",
        image: "/image1.png", // Đổi thành đường dẫn hình ảnh phù hợp
      },
      {
        name: "Chuyên gia tâm lý X",
        phone: "0981872467",
        address: "Hà Nội",
        image: "/image1.png", // Đổi thành đường dẫn hình ảnh phù hợp
      },
      {
        name: "Chuyên gia tâm lý X",
        phone: "0981872467",
        address: "Hà Nội",
        image: "/image1.png", // Đổi thành đường dẫn hình ảnh phù hợp
      },
    ];
  
    return (
      <div className="w-full bg-gradient-to-b from-blue-100 to-white py-10 px-6">
        <h2 className="text-center text-2xl font-bold text-teal-700 mb-6">
          Liên hệ với những chuyên gia tâm lý hàng đầu Việt Nam
        </h2>
        <div className="flex justify-center gap-6 flex-wrap">
          {counselors.map((counselor, index) => (
            <div key={index} className="bg-blue-600 text-white p-6 rounded-xl w-72 shadow-lg flex flex-col items-center">
              <img src={counselor.image} alt="avatar" className="w-16 h-16 mb-4" />
              <h3 className="font-bold text-lg">{counselor.name}</h3>
              <p className="text-sm">SDT: {counselor.phone}</p>
              <p className="text-sm">Địa chỉ: {counselor.address}</p>
            </div>
          ))}
        </div>
      </div>
    );
  }
  