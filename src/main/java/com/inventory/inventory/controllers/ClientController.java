package com.inventory.inventory.controllers;

import com.inventory.inventory.Application;
import com.inventory.inventory.businesslayer.entity.Client;
import com.inventory.inventory.businesslayer.entity.ClientCart;
import com.inventory.inventory.businesslayer.implementation.ClientServiceImpl;
import com.inventory.inventory.businesslayer.services.ClientService;
import com.inventory.inventory.dao.implementation.ClientCardDaoServiceImpl;
import com.inventory.inventory.dao.implementation.ClientDaoServiceImpl;
import com.inventory.inventory.dao.services.ClientCartDaoService;
import com.inventory.inventory.dao.services.ClientDaoService;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private Button removeClientBtn;

    @FXML
    private TableColumn<Client, String> firstName;

    @FXML
    private TableColumn<Client, String> lastName;

    @FXML
    private TableColumn<Client, String> phoneNumber;

    @FXML
    private TableView<Client> tableView;

    @FXML
    private Button addClientBtn;

    @FXML
    private Button clientCartBtn;

    @FXML
    private TableColumn<Client, Long> id;

    @FXML
    private Button goBackBtn;

    @FXML
    private TableColumn<Client, String> email;

    @FXML
    private TableColumn<Client, LocalDate> addedOn;
    private ClientDaoService clientDaoService;
    private ClientService clientService;
    private ClientCartDaoService clientCartDaoService;
    private Stage stage;

    private Boolean privilege;

    private String userName;

    public void setPrivilege(Boolean privilege) {
        this.privilege = privilege;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTables();
        initServices();
    }

    private void initServices() {
        clientDaoService = new ClientDaoServiceImpl();
    }

    private void initTables() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addedOn.setCellValueFactory(new PropertyValueFactory<>("addedOn"));
        tableView.getItems().addAll(getObservableList());
    }

    public ObservableList<Client> getObservableList() {
        ObservableList<Client> list = FXCollections.observableArrayList();
        try {
            clientDaoService = new ClientDaoServiceImpl();
            list.addAll(clientDaoService.AllClients());
            return list;
        } catch (Exception e) {
            AlertBox.display("Alert", "Cannot connect to Database");
            e.printStackTrace();
        }
        return list;
    }


    @FXML
    private void addClientBtnAction(ActionEvent event) {
        clientService = new ClientServiceImpl();
        stage = new Stage();

        /* firstName[]: start*/
        TextField firstName = new TextField();
        firstName.setPrefSize(176, 37);
        firstName.setPromptText("Enter First Name");
        /* firstName[]: end*/

        /* lastName[]: start*/
        TextField lastName = new TextField();
        lastName.setPrefSize(176, 37);
        lastName.setPromptText("Enter Last Name");
        /* lastName[]: end*/

        /* email[]: start*/
        TextField email = new TextField();
        email.setPrefSize(176, 37);
        email.setPromptText("example@gmail.com");
        /* email[]: end*/

        /* phoneNumber[]: start*/
        TextField phoneNumber = new TextField();
        phoneNumber.setPrefSize(176, 37);
        phoneNumber.setPromptText("Example: +4235789211");
        /* phoneNumber[]: end*/

        /*buttons[]: start*/
        Button save = new Button("Save");
        save.setPrefSize(120, 45);
        Button close = new Button("Close");
        close.setPrefSize(120, 45);
        /*buttons[]: end*/
        save.setOnAction(e -> {
            try {
                clientService.addClient(firstName.getText(), lastName.getText(), email.getText(), phoneNumber.getText());
                clientDaoService.addClient(clientService.getClient());
                tableView.getItems().clear();
                tableView.getItems().addAll(getObservableList());
                stage.close();
            } catch (Exception ex) {
                AlertBox.display("Alert", ex.getMessage());
            }
        });
        close.setOnAction(e -> stage.close());
        VBox vBox = new VBox();
        vBox.getChildren().addAll(firstName, lastName, email, phoneNumber, save, close);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setPrefSize(266, 400);
        vBox.setFillWidth(false);
        stage.setTitle("Add Client");
        stage.setScene(new Scene(vBox));
        stage.setResizable(false);
        stage.getIcons().add(new Image(Application.class.getResourceAsStream("icons/warehouse.png")));
        stage.show();
    }

    @FXML
    private void removeClientBtnAction(ActionEvent event) {
        try {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                clientDaoService.removeClient(tableView.getSelectionModel().getSelectedItem());
                tableView.getItems().clear();
                tableView.getItems().addAll(getObservableList());
            } else AlertBox.display("Alert", "You need to select client");
        } catch (RuntimeException e) {
            AlertBox.display("Alert", "Client has products in his cart");
        }

    }

    @FXML
    private void clientCartBtnAction(ActionEvent event) {
        clientCartDaoService = new ClientCardDaoServiceImpl();
        stage = new Stage();
        ObservableList<ClientCart> observableList;

        /*vBox[]: start*/
        VBox vBox = new VBox();
        vBox.setPrefSize(100, 255);
        vBox.setLayoutX(497);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        Button remove = new Button("Remove Item");
        remove.setPrefSize(90, 40);
        Button close = new Button("Close");
        close.setPrefSize(90, 40);
        vBox.getChildren().addAll(remove, close);
        /*vBox[]: end*/

        /*hBox[]: start*/
        HBox hBox = new HBox();
        hBox.setPrefSize(500, 244);
        ListView<ClientCart> listView = new ListView<>();
        listView.setPrefSize(500, 250);
        hBox.getChildren().add(listView);
        /*hBox[]: end*/

        /*pane[]: start*/
        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(600, 244);
        pane.getChildren().addAll(hBox, vBox);
        /*pane[]: end*/

        if (tableView.getSelectionModel().getSelectedItem() != null) {
            observableList = FXCollections.observableArrayList();
            var client = tableView.getSelectionModel().getSelectedItem();
            observableList.addAll(clientCartDaoService.getByClient(client));
            listView.getItems().addAll(observableList);
            stage.setScene(new Scene(pane));
            stage.setResizable(false);
            stage.setTitle("Remove Product");
            stage.getIcons().add(new Image(Application.class.getResourceAsStream("icons/warehouse.png")));
            stage.show();
            remove.setOnAction(e -> {
                if (listView.getSelectionModel().getSelectedItem() != null) {
                    clientCartDaoService.removeFromClientCart(listView.getSelectionModel().getSelectedItem());
                    listView.getItems().clear();
                    listView.getItems().addAll(clientCartDaoService.getByClient(client));
                } else AlertBox.display("Alert", "You need to select product");
            });
            close.setOnAction(e -> stage.close());
        } else AlertBox.display("Alert", "You need to select client");
    }

    @FXML
    private void goBackBtnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/PanelScene.fxml"));
        Parent root = fxmlLoader.load();
        PanelController panelController = fxmlLoader.getController();
        panelController.setPrivilege(privilege);
        panelController.setLabelField(userName);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Panel");
        stage.show();
    }
}
