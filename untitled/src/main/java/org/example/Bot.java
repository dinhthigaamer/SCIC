package org.example;
import okhttp3.*;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class Bot {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .build();

    private static final String API_URL = "http://127.0.0.1:5000/predict";

    private ChatManager chatManager;

    public Bot(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    public String reply(String message) {
        try {
            // 1️⃣ Tạo JSON request body
            JSONObject json = new JSONObject();
            json.put("context", message);  // Nội dung chat gửi đến model

            // 2️⃣ Định nghĩa request body
            RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));

            // 3️⃣ Gửi request
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .build();

            // 4️⃣ Nhận response
            Response response = client.newCall(request).execute();
            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getString("response"); // Lấy nội dung chatbot trả lời
//            try (Response response = client.newCall(request).execute()) {
//                if (response.body() != null) {
//                    JSONObject jsonResponse = new JSONObject(response.body().string());
//                    return jsonResponse.getString("response"); // Lấy nội dung chatbot trả lời
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Lỗi khi gọi chatbot!";
    }
}
