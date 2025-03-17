package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MethodController {

    public Label name;
    public ImageView image;
    public Label step;
    public AnchorPane Pane;

    public void setName(String name) {
        this.name.setText(name);
    }
    public void setImage(String imageLink) {
        Image newImage = new Image(getClass().getResource(imageLink).toExternalForm());
        image.setImage(newImage);
    }
    public void setStep(String step) {
        this.step.setText(step);
    }
    public void setBackground(String color) {
        Pane.setBackground(new Background(new BackgroundFill(
                Color.web(color), // Mã màu hex (đổi thành màu bạn muốn)
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));
    }
    @FXML
    public void initialize() {
    }
}
