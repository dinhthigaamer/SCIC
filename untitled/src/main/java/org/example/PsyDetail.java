package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PsyDetail {
    @FXML
    private Label nameLa;
    @FXML
    private Label addressLa;
    @FXML
    private Label phoneLa;

    @FXML
    public void initialize() {}

    public PsyDetail() {}

    public void setName(String name) {
        nameLa.setText(name);
    }

    public void setAddress(String address) {
        addressLa.setText(address);
    }

    public void setPhone(String phone) {
        phoneLa.setText(phone);
    }
}
