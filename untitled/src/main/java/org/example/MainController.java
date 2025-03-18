package org.example;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Pane contentPane; // Khu vực để hiển thị các trang
    @FXML private Button Button1, Button2, Button3, Button4;

    Button[] buttons = new Button[4];
    int flag = 0;

    @FXML private VBox menuContainer;

    public Scene mainScene;
    private Stage primaryStage;

    public void setPrimaryStage(Stage stage, Scene scene) {
        this.primaryStage = stage;
        this.mainScene = scene;
    }

    public void showPage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);
            contentPane.requestLayout();
        } catch (Exception e) {
            System.out.println("FXML load failed");
            e.printStackTrace();
        }
    }

    private void setButtons() {
        buttons[0] = Button1;
        buttons[1] = Button2;
        buttons[2] = Button3;
        buttons[3] = Button4;
    }

    void changeColor(int id) {
        buttons[flag].setStyle("-fx-background-color: #ffffff");
        buttons[id].setStyle("-fx-background-color: #1CAFF3");
        flag = id;
    }

    void setUpButtons(){
        changeColor(0);
        Button1.setOnAction(e -> {showPage("/HomePage.fxml"); changeColor(0);});
        Button2.setOnAction(e -> {showPage("/ChatPage(1).fxml"); changeColor(1);});
        Button3.setOnAction(e -> {System.out.println(3);});
        Button4.setOnAction(e -> {showPage("/ThuVienTinhThan.fxml"); changeColor(3);});
    }

    public void initialize(){}
    public MainController() {}
    @FXML
    public void init() {
        contentPane.setOnMouseClicked(e -> {System.out.println("Clicked");});

        setButtons();
        setUpButtons();
        showPage("/HomePage.fxml");
    }
}
