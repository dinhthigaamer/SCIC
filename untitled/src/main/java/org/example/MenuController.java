package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button Button1, Button2, Button3, Button4;

    public MenuController() {}

    public Button getBtn1() { return Button1; }
    public Button getBtn2() { return Button2; }
    public Button getBtn3() { return Button3; }
    public Button getBtn4() { return Button4; }

    void test() {
        System.out.println(getBtn1().getText());
    }
    @FXML
    public void initialize() {
    }
}
