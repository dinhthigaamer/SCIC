package org.example.api;

import org.example.model.*;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import static org.example.api.API.BASE_URL;
import static org.example.api.API.client;

public class AddUser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String addUser(User user) {
        String url = BASE_URL + "/user";
        try {
            // Chuyển đối tượng User thành JSON
            String userJson = objectMapper.writeValueAsString(user);
            System.out.println(userJson);

            // Tạo request body
            RequestBody body = RequestBody.create(userJson, MediaType.get("application/json; charset=utf-8"));

            // Gửi request POST
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            // Nhận phản hồi từ server
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return "Lỗi API: " + response.code();
                }
                return response.body().string(); // Trả về JSON từ server
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi gọi API";
        }
    }
}
