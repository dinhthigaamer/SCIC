package org.example;

public class Bot {
    private ChatManager chatManager;

    public Bot(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    // Trả tin nhắn về cho ChatController
    public String reply(String message) {
        return new String("Hi người đẹp");
    }
}
