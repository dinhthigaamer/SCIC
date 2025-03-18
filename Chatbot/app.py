from flask import Flask, request, jsonify
from model_loader import load_model
from inference import model_response
import user_config
import concurrent.futures

tokenizer = None
model = None
executor = concurrent.futures.ThreadPoolExecutor(max_workers=1)  # Chỉ 1 luồng để xử lý tuần tự

app = Flask(__name__)

def init():
    """Khởi tạo model và tokenizer"""
    global tokenizer, model
    model_path = "Chatbot/mental-health-counselor"
    tokenizer, model = load_model(model_path)
    print("✅ Model loaded")

@app.before_request
def load():
    """Chạy model khi API được khởi động"""
    if not hasattr(app, 'model_loaded'):
        init()
        app.model_loaded = True

@app.route("/")
def index():
    return "Chatbot API is running."

def process_request(data):
    """Xử lý dự đoán trên luồng riêng"""
    global tokenizer, model
    try:
        context = data.get("context", "")
        age = data.get("age", user_config.age)
        gender = data.get("gender", user_config.gender)
        marital_status = data.get("marital_status", user_config.marital_status)
        cbt_technique = data.get("cbt_technique", user_config.cbt_technique)

        response, time_taken = model_response(
            model, tokenizer,
            age=age,
            gender=gender,
            marital_status=marital_status,
            cbt_technique=cbt_technique,
            context=context
        )
        model_only_response = response.split("User:")[0].strip()

        return {
            "response": model_only_response,
            "time_taken": round(time_taken, 2)
        }
    except Exception as e:
        return {"error": str(e)}

@app.route("/predict", methods=["POST"])
def predict():
    """Gọi model trên luồng riêng, không chặn API"""
    data = request.get_json()

    # Gửi yêu cầu đến luồng riêng
    future = executor.submit(process_request, data)

    # Lấy kết quả từ luồng riêng
    result = future.result()

    print(f"📌 API Response: {result}")
    return jsonify(result)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
