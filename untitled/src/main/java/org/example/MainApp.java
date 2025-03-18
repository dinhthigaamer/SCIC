package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {
    MainController mainController;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Pane root = loader.load();
//        primaryStage.setResizable(false);
        mainController = loader.getController();

        mainController.init();
        mainController.setPrimaryStage(primaryStage, new Scene(root));

        primaryStage.setScene(mainController.mainScene);
        primaryStage.setTitle("Chatbot tâm lý");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
