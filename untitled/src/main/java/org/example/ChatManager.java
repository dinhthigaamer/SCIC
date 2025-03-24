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
import okhttp3.Response;
import org.example.api.GetChatHistory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ChatManager {
    private final VBox chatVBox; // VBox chứa các tin nhắn
    private final ScrollPane scrollPane;
    private final String EXTERNAL_FILE_PATH = "untitled/ChatData.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final int MAX_MESSAGES = 20; // Giới hạn số tin nhắn hiển thị

    public ChatManager(VBox chatVBox, ScrollPane scrollPane) {
        this.chatVBox = chatVBox;
        this.scrollPane = scrollPane;
        updateChatUI(loadChatData());
    }

    // 🔹 Đọc file ChatData.json từ resources
    public ArrayNode loadChatData() {
        try {
            String history = GetChatHistory.getHistory(UserDataManager.getId(), -1);
            System.out.println(history);
            System.out.println(history);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(history);

            if(rootNode != null  && rootNode.has("history")) {
                JsonNode jsonNode = rootNode.get("history");

                if (jsonNode.isArray()) {
                    ArrayNode arrayNode = (ArrayNode) jsonNode;

                    return arrayNode;
                } else {
                    System.out.println("The JSON is not an array.");
                    return null;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    void messageContainer(String sender, String message) {
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
        chatVBox.getChildren().add( messageContainer );
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
                String sender = messageNode.get("role").asText();
                String message = messageNode.get("content").asText();

                // 🔹 Tạo label hiển thị nội dung tin nhắn
                messageContainer(sender, message);

                if (chatVBox.getChildren().size() > MAX_MESSAGES) {
                    chatVBox.getChildren().remove(0);
                }

                scrollToBottom();
            }
        });
    }

    public void addMessage(String sender, String message) {
//        addMessageToJson(sender, message);
//        new Thread(() -> {
//            updateChatUI(loadChatData());
//        }).start();
        messageContainer(sender, message);
        scrollToBottom();
    }

    private void scrollToBottom() {
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }
}
