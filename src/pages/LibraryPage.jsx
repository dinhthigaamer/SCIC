export default function LibraryPage() {
    const therapies1 = [
      { title: "Bài test mức độ căng thẳng", icon: "⚡" },
      { title: "Bài test lo âu & trầm cảm (DASS-21)", icon: "🌧️" },
      { title: "Bài test suy nghĩ tiêu cực (ATQ)", icon: "🧠" },
    ];
    
    const therapies2 = [
      { title: "Chuyên gia của bạn đề xuất", icon: "💊", exercises: 5 },
      { title: "Thiền và thư giãn", icon: "🧘", exercises: 5 },
      { title: "Vẽ màu thư giãn", icon: "🎨", exercises: 5 },
    ];
  
    return (
      <div className="flex flex-col gap-8 p-8 bg-gray-100 min-h-screen">
        {/* Phần đầu tiên */}
        <section className="p-6 bg-cyan-100 rounded-lg text-center">
          <h2 className="text-2xl font-bold mb-4">Đánh giá trạng thái cảm xúc của bạn</h2>
          <div className="flex justify-center gap-6">
            {therapies1.map((therapy, index) => (
              <div
                key={index}
                className="p-6 bg-gradient-to-b from-teal-400 to-teal-600 text-white rounded-lg w-1/4 text-center shadow-lg"
              >
                <div className="text-4xl mb-2">{therapy.icon}</div>
                <p className="font-semibold">{therapy.title}</p>
              </div>
            ))}
          </div>
        </section>
  
        {/* Phần thứ hai */}
        <section className="p-6 bg-pink-100 rounded-lg text-center">
          <h2 className="text-2xl font-bold mb-4">
            Thực hành những bài tập này thường xuyên để giữ tinh thần thoải mái 😊
          </h2>
          <div className="flex justify-center gap-6">
            {therapies2.map((therapy, index) => (
              <div
                key={index}
                className="p-6 bg-gradient-to-b from-indigo-400 to-indigo-600 text-white rounded-lg w-1/4 text-center shadow-lg"
              >
                <div className="text-4xl mb-2">{therapy.icon}</div>
                <p className="font-semibold">{therapy.title}</p>
                <p className="text-sm">{therapy.exercises} bài tập</p>
              </div>
            ))}
          </div>
        </section>
      </div>
    );
  }
  