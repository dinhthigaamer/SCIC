package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ChatManager {
    private final VBox chatVBox; // VBox chứa các tin nhắn
    private final ScrollPane scrollPane;
    private final String EXTERNAL_FILE_PATH = "untitled/ChatData.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public ChatManager(VBox chatVBox, ScrollPane scrollPane) {
        this.chatVBox = chatVBox;
        this.scrollPane = scrollPane;
        updateChatUI(loadChatData());
    }

    public void addMessageToJson(String sender, String message) {
        try {
            System.out.println(sender + " " + message);
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(EXTERNAL_FILE_PATH);

            ArrayNode chatHistory;
            if (file.exists()) {
                // Đọc dữ liệu cũ từ file JSON
                JsonNode rootNode = mapper.readTree(file);
                chatHistory = (ArrayNode) rootNode;
                System.out.println("✅ Tin nhắn đã được lưu vào ChatData.json!");
            } else {
                // Nếu file chưa tồn tại, tạo mới một mảng JSON
                chatHistory = mapper.createArrayNode();
            }

            // Tạo một ObjectNode cho tin nhắn mới
            ObjectNode newMessage = mapper.createObjectNode();
            newMessage.put("sender", sender);
            newMessage.put("message", message);

            // Thêm tin nhắn mới vào mảng JSON
            chatHistory.add(newMessage);

            // Ghi lại file JSON
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, chatHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Đọc file ChatData.json từ resources
    public ArrayNode loadChatData() {
        try {
            File file = new File(EXTERNAL_FILE_PATH);
            if (!file.exists()) {
                System.out.println("File Not Found");
            }

            JsonNode rootNode = mapper.readTree(file);
            return (ArrayNode) rootNode;
        } catch (IOException e) {
            e.printStackTrace();
            return mapper.createArrayNode(); // Trả về mảng rỗng nếu lỗi
        }
    }

    // 🔹 Cập nhật giao diện với dữ liệu chat
    public void updateChatUI(JsonNode chatData) {
        if (chatData == null || !chatData.isArray()) {
            System.out.println("⚠ Dữ liệu chat không hợp lệ!");
            return;
        }

        Platform.runLater(() -> {
            chatVBox.getChildren().clear(); // Xóa nội dung cũ

            for (JsonNode messageNode : chatData) {
                String sender = messageNode.get("sender").asText();
                String message = messageNode.get("message").asText();

                // 🔹 Tạo label hiển thị nội dung tin nhắn
                Label messageLabel = new Label(message);
                messageLabel.setPadding(new Insets(10));
                messageLabel.setWrapText(true);
                messageLabel.setStyle(
                        "-fx-background-radius: 15px; " +
                                "-fx-padding: 10px; " +
                                "-fx-text-fill: white; " +
                                "-fx-font-size: 14px;" +
                                "-fx-background-color: rgba(0, 0, 0, 0.5);"); // Nền trong suốt 50%
                messageLabel.setWrapText(true);
//                messageLabel.setMaxWidth(scrollPane.getWidth() * 0.7);

                // 🔹 HBox chứa tin nhắn
                HBox messageContainer = new HBox();
                messageContainer.setPadding(new Insets(5, 10, 5, 10));

                messageContainer.setHgrow(messageLabel, Priority.ALWAYS);

                if ("user".equals(sender)) {
                    // Tin nhắn của người dùng (căn phải)
                    messageLabel.setStyle(messageLabel.getStyle() + "-fx-background-color: rgba(30, 144, 255, 0.5);"); // Xanh dương nhạt
                    messageContainer.setAlignment(Pos.CENTER_RIGHT);
                } else {
                    // Tin nhắn của bot (căn trái)
                    messageLabel.setStyle(messageLabel.getStyle() + "-fx-background-color: rgba(50, 50, 50, 0.5);"); // Xám nhạt
                    messageContainer.setAlignment(Pos.CENTER_LEFT);
                }

                messageContainer.getChildren().add(messageLabel);
                chatVBox.getChildren().add(messageContainer);

                scrollToBottom();
            }
        });
    }

    public void addMessage(String sender, String message) {
        addMessageToJson(sender, message);
        updateChatUI(loadChatData());
    }

    private void scrollToBottom() {
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }
}
