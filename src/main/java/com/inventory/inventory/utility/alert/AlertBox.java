package com.inventory.inventory.utility.alert;

import com.inventory.inventory.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
        public static void display(final String title, final String message){
            Stage stage = new Stage();

            Label label = new Label(message);

            Button closeButton = new Button("OK");
            closeButton.setOnAction(e -> stage.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            initWindow(stage, new Scene(layout), title);
        }

        private static void initWindow(Stage stage, final Scene scene, final String title){
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setMinWidth(250);
            stage.setMinHeight(70);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(Application.class.getResourceAsStream("icons/close.png")));
            stage.showAndWait();
        }
    }
