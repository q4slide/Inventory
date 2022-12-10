package com.inventory.inventory.controllers;

import com.inventory.inventory.Application;
import com.inventory.inventory.dao.implementation.UserDaoServiceImpl;
import com.inventory.inventory.dao.services.UserDaoService;
import com.inventory.inventory.utility.alert.AlertBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private UserDaoService userDaoService;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private PasswordField passwordField;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initServices();
    }

    private void initServices() {
        userDaoService = new UserDaoServiceImpl();
    }

    @FXML
    private void loginBtnAction(ActionEvent event) {
        try {
            if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty() ||
                    usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                AlertBox.display("Error", "Wrong username or password");
            } else {
                var user = userDaoService.login(usernameField.getText(), passwordField.getText());
                if (user != null) {
                    FXMLLoader loader = new FXMLLoader(Application.class.getResource("fxml/PanelScene.fxml"));
                    Parent root = loader.load();
                    PanelController panelController = loader.getController();
                    panelController.setLabelField(user.getUsername());
                    panelController.setPrivilege(user.isAdmin());
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.setTitle("Panel");
                    stage.show();
                } else AlertBox.display("Alert", "Wrong username or password");
            }
        } catch (IOException exception) {
            AlertBox.display("Alert", exception.getMessage());
        }
    }

    @FXML
    private void closeBtnAction() {
        Platform.exit();
    }


}
