package org.example.api;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class API {
    public static final String BASE_URL = "https://9d8d-35-186-145-38.ngrok-free.app";
    public static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // ⏳ Thời gian chờ kết nối
            .readTimeout(30, TimeUnit.SECONDS)    // ⏳ Thời gian chờ đọc dữ liệu
            .writeTimeout(30, TimeUnit.SECONDS)   // ⏳ Thời gian chờ ghi dữ liệu
            .build();
}
