package com.inventory.inventory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("fxml/MainScene.fxml"));
        stage.getIcons().add(new Image(Application.class.getResourceAsStream("icons/warehouse.png")));
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}