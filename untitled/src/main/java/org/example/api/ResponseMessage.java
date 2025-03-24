package org.example.api;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.*;

import static org.example.api.API.BASE_URL;
import static org.example.api.API.client;

public class ResponseMessage {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static Future<String> sendMessage(String userId, String message) {
        return executor.submit(() -> {
            String url = BASE_URL + "/chat/" + userId;
            System.out.println("Sending request to " + url);

            String jsonRequest = "{\"message\": \"" + message + "\"}";
            System.out.println(jsonRequest);

            RequestBody body = RequestBody.create(jsonRequest, MediaType.get("application/json"));
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseString = response.body().string();
                System.out.println(responseString);

                JSONObject responseJson = new JSONObject(responseString);
                return responseJson.optString("response", "Không có câu trả lời");
            } catch (IOException e) {
                return "Gọi API không thành công";
            }
        });
    }
}
