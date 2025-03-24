package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.api.ResponseMessage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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

    private void sendMessage() throws IOException {
        String message = textArea.getText();
        if(message == null || message.equals("")) {return;}
        chatManager.addMessage("user", message);
        textArea.clear();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            chatManager.addMessage("assistant", ResponseMessage.sendMessage(UserDataManager.getId(), message).get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {
        sendButton.setOnMouseClicked(e -> {
            try {
                sendMessage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @FXML
    public void initialize() {
        chatManager = new ChatManager(chatBox, scrollPane); // Khởi tạo ChatManager
//        chatData = chatManager.loadChatData();  // Lưu dữ liệu chat
//        bot = new Bot(chatManager);

        init();
    }

    // 🔹 Cập nhật lại giao diện khi có thay đổi dữ liệu chat
    public void refreshChat() {
        if (chatData != null) {
            chatManager.updateChatUI(chatData);
        }
    }
}
