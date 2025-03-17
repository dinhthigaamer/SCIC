package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.example.datatoshow.Method;

import java.io.IOException;

public class Content2 {
    public FlowPane flowPane;

    public void initialize(){}
    public Content2(){}

    @FXML
    private AnchorPane anchorPane;

    public Node getNode(Method[] methods){
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(50);
        flowPane.setVgap(50);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setPrefWrapLength(1500);

        for(Method m : methods){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MethodDetail.fxml"));
                Node root = loader.load();

                MethodController me =  loader.getController();
                me.setName(m.getName());
                me.setBackground(m.getBackgroundColor());
                me.setImage(m.getImageLink());

                flowPane.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return anchorPane;
    }
}
