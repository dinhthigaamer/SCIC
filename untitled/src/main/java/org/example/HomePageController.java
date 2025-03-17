package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import org.example.datatoshow.Psychologist;

import java.io.IOException;

public class HomePageController {
    @FXML public VBox vBox;
    @FXML public HBox lmao;
    public Button LienHe;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    HBox hBox;

    Psychologist[] psychologist;
    @FXML
    public void initialize() {
        psychologist = new Psychologist[7];
        psychologist[0] = new Psychologist("X", "0123456789", "Hà Nội");
        psychologist[1] = new Psychologist("X", "0123456789", "Hà Nội");
        psychologist[2] = new Psychologist("X", "0123456789", "Hà Nội");
        psychologist[3] = new Psychologist("X", "0123456789", "Hà Nội");
        psychologist[4] = new Psychologist("X", "0123456789", "Hà Nội");
        psychologist[5] = new Psychologist("X", "0123456789", "Hà Nội");
        psychologist[6] = new Psychologist("X", "0123456789", "Hà Nội");

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
//        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPrefWrapLength(1500); // Khi đạt 300px, sẽ tự động xuống hàng

        for(int i = 0; i < psychologist.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/psychologist_detail.fxml"));
                Node anchorPane = loader.load();

                PsyDetail psyDetail = loader.getController();

                psyDetail.setPhone("SĐT: " + psychologist[i].getPhone());
                psyDetail.setName("Chuyên gia: " + psychologist[i].getName());
                psyDetail.setAddress("Địa chỉ: " + psychologist[i].getAddress());

                flowPane.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        hBox.getChildren().add(flowPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        lmao.setMinHeight(Region.USE_PREF_SIZE);
        scrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE);

        LienHe.setOnAction(event -> {
            Platform.runLater(() -> scrollPane.setVvalue(1.0));
        });
    }

    public HomePageController() {}
}
