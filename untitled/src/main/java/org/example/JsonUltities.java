package org.example;

import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUltities {
    private static final String FILE_PATH = "user_data.json";

    public static boolean isJsonFileEmpty() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(FILE_PATH));

            return jsonNode == null || jsonNode.isEmpty();
        } catch (Exception e) {
            return true; // Lỗi khi đọc file -> coi như rỗng
        }
    }
}
