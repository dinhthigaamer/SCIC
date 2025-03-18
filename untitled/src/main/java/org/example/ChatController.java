package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController {
    @FXML
    public TextArea textArea;
    @FXML
    public ImageView sendButton;
    public VBox VBox;
    @FXML
    private VBox chatBox; // VBox chứa các tin nhắn

    @FXML
    private ScrollPane scrollPane;

    private ChatManager chatManager; // Quản lý dữ liệu chat
    private JsonNode chatData; // Dữ liệu chat từ JSON
    private Bot bot;

    private void sendMessage() {
        String message = textArea.getText();
        if(message == null || message.equals("")) {return;}
        chatManager.addMessage("user", message);
        textArea.clear();

        Platform.runLater(() -> {
            chatManager.addMessage("bot", bot.reply(message));
        });
    }

    private void init() {
        sendButton.setOnMouseClicked(e -> sendMessage());
    }

    @FXML
    public void initialize() {
        chatManager = new ChatManager(chatBox, scrollPane); // Khởi tạo ChatManager
        chatData = chatManager.loadChatData();  // Lưu dữ liệu chat
        bot = new Bot(chatManager);

        init();
    }

    // 🔹 Cập nhật lại giao diện khi có thay đổi dữ liệu chat
    public void refreshChat() {
        if (chatData != null) {
            chatManager.updateChatUI(chatData);
        }
    }
}
