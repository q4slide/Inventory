package com.inventory.inventory.controllers;

import com.inventory.inventory.Application;
import com.inventory.inventory.businesslayer.entity.User;
import com.inventory.inventory.businesslayer.implementation.UserServiceImpl;
import com.inventory.inventory.businesslayer.services.UserService;
import com.inventory.inventory.dao.implementation.UserDaoServiceImpl;
import com.inventory.inventory.dao.services.UserDaoService;
import com.inventory.inventory.utility.alert.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private Button removeUserBtn;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private Button addUserBtn;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Boolean> admin;

    @FXML
    private TableColumn<User, Long> id;

    @FXML
    private Button goBackBtn;

    @FXML
    private TableColumn<User, String> username;
    private UserService userService;
    private UserDaoService userDaoService;
    private Stage stage;
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initServices();
        initTables();
    }

    private void initTables() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        admin.setCellValueFactory(new PropertyValueFactory<>("admin"));
        tableView.getItems().addAll(getObservableList());
    }

    private void initServices() {
        userDaoService = new UserDaoServiceImpl();
    }

    private ObservableList<User> getObservableList() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            list.addAll(userDaoService.allUsers());
            return list;
        } catch (Exception e) {
            AlertBox.display("Alert", "Cannot connect to Database");
            e.printStackTrace();
        }
        return list;
    }


    @FXML
    void addUserBtnAction(ActionEvent event) {
        userService = new UserServiceImpl();
        stage = new Stage();
        /*vBox[]: start*/
        Label label = new Label("Add User");
        TextField username = new TextField();
        username.setPromptText("Username");
        TextField password = new TextField();
        password.setPromptText("Password");
        CheckBox admin = new CheckBox("Admin");
        Button save = new Button("Save");
        Button close = new Button("Close");
        label.setFont(Font.font(28));
        save.setPrefSize(92, 40);
        close.setPrefSize(92, 40);
        VBox vBox = new VBox();
        vBox.setFillWidth(false);
        vBox.setPrefSize(300, 300);
        vBox.getChildren().addAll(label, username, password, admin, save, close);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        stage.setTitle("Add User");
        stage.setResizable(false);
        stage.setScene(new Scene(vBox));
        stage.getIcons().add(new Image(Application.class.getResourceAsStream("icons/warehouse.png")));
        stage.show();
        /*vBox[]:end*/

        save.setOnAction(e ->
        {
            if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                userService.addUser(username.getText(), password.getText(), admin.isSelected());
                userDaoService.addUser(userService.getUser());
                tableView.getItems().clear();
                tableView.getItems().addAll(getObservableList());
                stage.close();
            } else AlertBox.display("Alert", "Wrong information");
        });
        close.setOnAction(e -> stage.close());

    }

    @FXML
    void removeUserBtnAction(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            userDaoService.removeUser(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().clear();
            tableView.getItems().addAll(getObservableList());
        } else AlertBox.display("Alert", "You need to select user");
    }

    @FXML
    void goBackBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/PanelScene.fxml"));
            Parent root = fxmlLoader.load();
            PanelController panelController = fxmlLoader.getController();
            panelController.setLabelField(userName);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Panel");
            stage.show();
        } catch (Exception e) {
            AlertBox.display("Alert", e.getMessage());
        }
    }

}
