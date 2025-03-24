package org.example;

import java.io.*;

import org.example.api.AddUser;
import org.example.model.User;
import org.json.JSONObject;

import static org.example.api.AddUser.addUser;

public class UserDataManager {
    private static final String FILE_PATH = "user_data.json";
    private static User user;
    // 📤 Ghi dữ liệu vào file JSON từ tham số hàm
    public static void saveUserData(
            String name, String age, String gender, String maritalStatus,
            String occupation, String reasonForCounseling, String cbtTechnique
    ) {
        user = new User(name, age, gender, maritalStatus, occupation, reasonForCounseling, cbtTechnique);

        String jsonResponse = AddUser.addUser(user);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.out.println("❌ Lỗi khi nhận phản hồi từ API, không thể lưu dữ liệu.");
            return;
        }

        try {
            JSONObject response = new JSONObject(jsonResponse);
            if (!response.has("_id")) {
                System.out.println("❌ Phản hồi API không chứa user_id, không thể lưu.");
                return;
            }

            String userId = response.getString("_id");

            if (userId == null) {
                System.out.println("❌ Lỗi khi nhận ID từ API, không lưu được dữ liệu.");
                return;
            }

            // Lưu dữ liệu vào JSON
            JSONObject userData = new JSONObject();
            userData.put("_id", userId);
            userData.put("name", name);
            userData.put("age", age);
            userData.put("gender", gender);
            userData.put("marital_status", maritalStatus);
            userData.put("occupation", occupation);
            userData.put("reason_for_counseling", reasonForCounseling);
            userData.put("cbt_technique", cbtTechnique);

            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write(userData.toString(4)); // Ghi JSON đẹp
                System.out.println("✅ Dữ liệu đã được lưu vào " + FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
        ;
    }


    // 📥 Đọc dữ liệu từ file JSON
    private static JSONObject readUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            return new JSONObject(jsonContent.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject(); // Trả về JSON rỗng nếu có lỗi
        }
    }

    // 📥 Lấy user_id từ file JSON
    public static String getId() {
        JSONObject userData = readUserData();
        return userData.optString("_id", "User ID không tồn tại!");
    }

    // Giả sử đây là hàm gọi API addUser (bạn đã có class riêng)
    private static String addUser(User user) {
        // Gọi API addUser từ class bạn đã tạo và lấy kết quả JSON
        return addUser(user);
    }
}
