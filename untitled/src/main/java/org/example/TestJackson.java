package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class TestJackson {
    public static void main(String[] args) throws Exception {
        String json = "{\"name\": \"ChatGPT\", \"age\": 2}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(json, Map.class);

        System.out.println(data.get("name")); // ChatGPT
    }
}
