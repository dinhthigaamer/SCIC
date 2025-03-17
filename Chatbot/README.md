# 📁 Cấu trúc thư mục
mental-health-counsellor/
├── main.py
├── model_loader.py
├── inference.py
├── user_config.py
├── data/
│   └── sample_inputs.py
├── requirements.txt
└── README.md

# 🚀 Hướng dẫn chạy
## 1. Cài đặt thư viện cần thiết
```
pip install -r requirements.txt
```

## 2. Khởi tạo model
File main.py đã được chia thành hai phần chính:

init(): Tải mô hình và tokenizer
run_demo(): Chạy thử các phản hồi từ model

## 3. Cấu hình người dùng
Thông tin người dùng được lưu trong file user_config.py
Bạn có thể thay đổi các thông tin này tùy ý để cá nhân hóa phản hồi.

## 4. Thay đổi input cho thử nghiệm
Các câu hỏi hoặc tình huống tâm lý đầu vào được định nghĩa sẵn trong data/sample_inputs.py. Hệ thống sẽ xử lý từng dòng và sinh phản hồi tương ứng.
Bạn hoàn toàn có thể cập nhật các đoạn văn này để có các kiểm thử khác.