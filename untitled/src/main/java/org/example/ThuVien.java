package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.example.datatoshow.Method;

import java.io.IOException;

public class ThuVien {
    public VBox vbox;
    @FXML
    private Method[] method, healing;

    @FXML
    public void initialize(){
        method = new  Method[3];
        method[0] = new Method("Chuyên gia của bạn đề xuất", 5, "#3E837F", "/icons8-pharmacist-64 1.png");
        method[1] = new Method("Thiền và thư giãn", 5, "#5C44E1", "/icons8-meditation-100 (1) 1.png");
        method[2] = new Method("Vẽ màu thư giãn", 5, "#F5BFFF", "/icons8-art-64 1.png");

        healing = new Method[3];
        healing[0] = new Method("Bài test mức độ căng thẳng", 0, "#27E0D7", "/icons8-anxiety-100 1.png");
        healing[1] = new Method("Bài test lo âu & trầm cảm (DASS-21)", 0, "#5C44E1", "/icons8-depression-100 1.png");
        healing[2] = new Method("Bài test suy nghĩ tiêu cực \n" + "(ATQ)", 0, "#90EBE7", "/icons8-thinking-100 1.png");

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ThuVienTinhThan_content1.fxml"));
        try {
            Node root1 = loader1.load();
            Content1 content1 = loader1.getController();
            content1.getNode(method);
            vbox.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ThuVienTinhThan_content2.fxml"));
        try {
            Node root2 = loader2.load();
            Content2 content2 = loader2.getController();
            content2.getNode(healing);
            vbox.getChildren().add(root2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        vbox.setSpacing(50);
    }

    public ThuVien(){}
}
