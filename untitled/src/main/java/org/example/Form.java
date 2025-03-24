package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Form extends Application {

    private TextField nameField, ageField, occupationField;
    private ComboBox<String> genderBox, maritalBox;
    private TextArea reasonField, cbtField;
    private Label statusLabel;

    private void switchToHome(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Pane root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        MainController mainController = loader.getController();

        mainController.init();
        mainController.setPrimaryStage(primaryStage, new Scene(root));

        primaryStage.setScene(mainController.mainScene);
        primaryStage.setTitle("Chatbot tâm lý");
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        // Tiêu đề chính
        Label title = new Label("PsyTech");
        title.setFont(new Font("Arial", 22));
        title.setTextFill(Color.web("#007bff")); // Màu xanh dương
        title.setStyle("-fx-font-weight: bold;");

        // Hướng dẫn nhập thông tin (cỡ chữ nhỏ hơn)
        Label guideLabel = new Label("Bạn có thể điền một số thông tin, điều này giúp tư vấn hiệu quả hơn.");
        guideLabel.setFont(new Font("Arial", 13)); // Giảm kích thước chữ
        guideLabel.setTextFill(Color.web("#333"));
        guideLabel.setAlignment(Pos.CENTER);
        guideLabel.setWrapText(true);

        // Tạo các trường nhập
        nameField = createStyledTextField("Nhập họ và tên");
        ageField = createStyledTextField("Nhập tuổi");
        occupationField = createStyledTextField("Nghề nghiệp");

        // Giới tính và tình trạng hôn nhân trên cùng một dòng
        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Nam", "Nữ", "Khác");
        genderBox.setPromptText("Chọn giới tính");
        styleComboBox(genderBox);

        maritalBox = new ComboBox<>();
        maritalBox.getItems().addAll("Độc thân", "Kết hôn", "Khác");
        maritalBox.setPromptText("Tình trạng hôn nhân");
        styleComboBox(maritalBox);

        HBox genderMaritalBox = new HBox(10, genderBox, maritalBox);
        genderMaritalBox.setAlignment(Pos.CENTER);

        // Lý do tư vấn và kỹ thuật CBT
        reasonField = createStyledTextArea("Lý do tư vấn");
        cbtField = createStyledTextArea("Kỹ thuật CBT áp dụng");

        // Nút gửi
        Button submitButton = new Button("Bắt đầu");
        submitButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
        submitButton.setOnAction(e -> statusLabel.setText("Dữ liệu đã gửi!"));

        submitButton.setOnAction(e -> {
            UserDataManager.saveUserData(nameField.getText(), ageField.getText(), genderBox.getValue(),
                    maritalBox.getValue(), occupationField.getText(), reasonField.getText(), cbtField.getText());

            switchToHome(primaryStage);
        });
        statusLabel = new Label();
        statusLabel.setTextFill(Color.RED);

        // Tạo layout chính
        VBox layout = new VBox(10, title, guideLabel, nameField, ageField, genderMaritalBox, occupationField, reasonField, cbtField, submitButton, statusLabel);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f8f9fa; -fx-padding: 30px; -fx-border-radius: 10px;");

        // Cấu hình scene với kích thước cố định
        Scene scene = new Scene(layout, 500, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PsyTech - Nhập Thông Tin");
        primaryStage.setResizable(false); // Không cho thay đổi kích thước
        primaryStage.show();
    }

    // Hàm tạo TextField đẹp hơn
    private TextField createStyledTextField(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.setStyle("-fx-border-color: #ced4da; -fx-border-radius: 5px; -fx-padding: 8px;");
        return textField;
    }

    // Hàm tạo TextArea đẹp hơn
    private TextArea createStyledTextArea(String placeholder) {
        TextArea textArea = new TextArea();
        textArea.setPromptText(placeholder);
        textArea.setStyle("-fx-border-color: #ced4da; -fx-border-radius: 5px; -fx-padding: 8px;");
        return textArea;
    }

    // Hàm style ComboBox
    private void styleComboBox(ComboBox<String> comboBox) {
        comboBox.setStyle("-fx-border-color: #ced4da; -fx-border-radius: 5px; -fx-padding: 5px;");
        comboBox.setMinWidth(150);
    }
}
