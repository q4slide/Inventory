package com.inventory.inventory.controllers;

import com.inventory.inventory.Application;
import com.inventory.inventory.utility.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelController implements Initializable {


    @FXML
    private Button logoutBtn;
    @FXML
    private Button clientsBtn;
    @FXML
    private Button productsBtn;
    @FXML
    private Label labelField;
    @FXML
    private Button usersBtn;
    private Stage stage;
    private Parent root;
    private String name;
    private FXMLLoader fxmlLoader;

    public void setLabelField(String usersName) {
        labelField.setText("Hello " + usersName);
        labelField.setFont(Font.font(15));
        labelField.setStyle("-fx-font-weight:bold");
        this.name = usersName;
    }

    public void setPrivilege(Boolean isAdmin) {
        usersBtn.setVisible(isAdmin);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void usersBtnAction(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/UsersScene.fxml"));
            root = fxmlLoader.load();
            UserController userController = fxmlLoader.getController();
            userController.setUserName(name);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Users");
            stage.show();
        } catch (Exception e) {
            AlertBox.display("Alert", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void productsBtnAction(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/ProductsScene.fxml"));
            root = fxmlLoader.load();
            ProductController controller = fxmlLoader.getController();
            controller.setPrivilege(usersBtn.isVisible());
            controller.setUserName(name);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Products");
            stage.show();
        } catch (Exception e) {
            AlertBox.display("Alert", e.getMessage());
        }
    }

    @FXML
    private void clientsBtnAction(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/ClientsScene.fxml"));
            root = fxmlLoader.load();
            ClientController clientController = fxmlLoader.getController();
            clientController.setUserName(name);
            clientController.setPrivilege(usersBtn.isVisible());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Clients");
            stage.show();
        } catch (Exception e) {
            AlertBox.display("Alert", e.getMessage());
        }
    }

    @FXML
    private void logoutBtnAction(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/MainScene.fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            AlertBox.display("Error", e.getMessage());
        }
    }
}
