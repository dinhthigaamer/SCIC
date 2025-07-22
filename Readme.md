# Welcome to PsyTech!

**Mô tả dự án**
-
Dự án này là một chatbot tư vấn tâm lý, giúp người sử dụng giảm bớt lo âu và trong một số trường hợp cần thiết sẽ tư vấn phòng khám tâm lý phù hợp cũng như cảnh báo tới bác sỹ phụ trách nếu như người dùng có ý định tự hại.

**Khởi chạy phần Front-end**
-
- B1: Cài đặt Nodejs
- B2: Ở phần terminal, nhập **npm install** để tải tài nguyên cần thiết
- B3: Nhập **npm run dev** để bắt đầu và truy cập đường dẫn localhost được tạo ra.

**Khời chạy phần Back-end**
-
- Phần file .ipynb được chạy trên **Kaggle** (File này nó hơi kỳ, mình chạy trên colab thì bị lỗi). 
- Ở **Kaggle**:
	- Ấn vào create new notebook, vào edit sau đó import file .ipynb lên
	- Phần **Add input** bên phải, đẩy cái model kia lên, sau đó copy đường dẫn ở đấy rồi paste vào model_path ở code.
	- Chạy lần lượt từng đoạn code
	- Sau khi chạy xong đoạn code cuối, paste link ngrok sinh ra để paste vào phần target ở file vite-config ở phần backend.
	- Ở phần code có data sẵn, mọi người có thể dùng PostMan để test các API

**Nếu có lỗi gì thì mọi người tự fix và phát triển tiếp nhé.**
