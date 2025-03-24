package org.example.api;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import org.example.api.API.*;

import static org.example.api.API.BASE_URL;
import static org.example.api.API.client;

public class GetChatHistory {
    public static String getHistory(String userId, int index) throws IOException {
        String url = BASE_URL + "/history/" + userId + "/" + index;

        System.out.println("Sending request to " + url);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}

