package com.inventory.inventory.controllers;

import com.inventory.inventory.Application;
import com.inventory.inventory.businesslayer.entity.Client;
import com.inventory.inventory.businesslayer.entity.Product;
import com.inventory.inventory.businesslayer.implementation.ClientCartServiceImpl;
import com.inventory.inventory.businesslayer.implementation.ProductServiceImpl;
import com.inventory.inventory.businesslayer.services.ClientCartService;
import com.inventory.inventory.businesslayer.services.ProductService;
import com.inventory.inventory.dao.implementation.ClientCardDaoServiceImpl;
import com.inventory.inventory.dao.implementation.ProductDaoServiceImpl;
import com.inventory.inventory.dao.services.ClientCartDaoService;
import com.inventory.inventory.dao.services.ProductDaoService;
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

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private TableColumn<Product, BigDecimal> price;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> description;
    @FXML
    private TableColumn<Product, Boolean> available;
    @FXML
    private TableColumn<Product, Long> id;
    @FXML
    private TableColumn<Product, LocalDate> addedOn;
    @FXML
    private Button assignToBtn;
    @FXML
    private Button goBackBtn;
    @FXML
    private Button addProductBtn;
    @FXML
    private Button removeProductBtn;
    private ProductDaoService productDaoService;
    private ProductService productService;
    private ClientCartService clientCartService;
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
        initServices();
        initTables();
    }


    private void initServices() {
        productDaoService = new ProductDaoServiceImpl();
    }

    private void initTables() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addedOn.setCellValueFactory(new PropertyValueFactory<>("addedOn"));
        available.setCellValueFactory(new PropertyValueFactory<>("available"));
        tableView.getItems().addAll(getObservableList());
    }

    private ObservableList<Product> getObservableList() {
        ObservableList<Product> list = FXCollections.observableArrayList();
        try {
            list.addAll(productDaoService.productList());
            return list;
        } catch (Exception e) {
            AlertBox.display("Alert", "Cannot connect to Database");
            e.printStackTrace();
        }
        return list;
    }

    @FXML
    private void addProductBtnAction(ActionEvent event) {
        productService = new ProductServiceImpl();
        stage = new Stage();
        /*vBox:[] start*/
        Label label = new Label("Add Product");
        TextField name = new TextField();
        name.setPromptText("Product name");
        TextArea description = new TextArea();
        description.setPrefSize(220, 194);
        description.setPromptText("Product description");
        CheckBox available = new CheckBox("Available");
        TextField price = new TextField();
        price.setPromptText("Product price");
        Button save = new Button("Save");
        Button close = new Button("Close");
        save.setPrefSize(100, 40);
        close.setPrefSize(100, 40);
        label.setFont(Font.font(28));
        VBox vbox = new VBox();
        vbox.setPrefSize(323, 423);
        vbox.getChildren().addAll(label, name, description, available, price, save, close);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setFillWidth(false);
        stage.setTitle("Add Product");
        stage.setScene(new Scene(vbox));
        stage.setResizable(false);
        stage.getIcons().add(new Image(Application.class.getResourceAsStream("icons/warehouse.png")));
        stage.show();
        /*vBox[]: end*/
        save.setOnAction(e -> {
            if (!name.getText().isEmpty() && !description.getText().isEmpty() && !price.getText().isEmpty() && price.getText().matches("\\d*")) {
                productService.
                        addProduct(name.getText()
                                , description.getText()
                                , available.isSelected()
                                , new BigDecimal(price.getText()));
                productDaoService.addProduct(productService.getProduct());
                tableView.getItems().clear();
                tableView.getItems().addAll(getObservableList());
                stage.close();
            } else AlertBox.display("Alert", "Wrong information");
        });
        close.setOnAction(e -> stage.close());

    }

    @FXML
    private void removeProductBtnAction(ActionEvent event) {

        try {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                productDaoService.removeProduct(tableView.getSelectionModel().getSelectedItem());
                tableView.getItems().clear();
                tableView.getItems().addAll(getObservableList());
            } else AlertBox.display("Alert", "You need to select item");
        } catch (RuntimeException e) {
            AlertBox.display("Alert", "Product is assign to clients");
        }
    }

    @FXML
    private void assignToBtnAction(ActionEvent event) {
        try {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                if(tableView.getSelectionModel().getSelectedItem().isAvailable()) {
                    var product = tableView.getSelectionModel().getSelectedItem();
                    stage = new Stage();
                    /*vBox[]: start*/
                    Button select = new Button("Select");
                    select.setPrefSize(77, 28);
                    Button close = new Button("Close");
                    close.setPrefSize(77, 28);
                    ClientController clientController = new ClientController();
                    TableView<Client> clientTableView = new TableView<>(clientController.getObservableList());
                    TableColumn<Client, String> firstName = new TableColumn<>("First Name");
                    firstName.setPrefWidth(75);
                    TableColumn<Client, String> lastName = new TableColumn<>("Last Name");
                    lastName.setPrefWidth(75);
                    TableColumn<Client, String> phoneNumber = new TableColumn<>("Phone Number");
                    phoneNumber.setPrefWidth(100);
                    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                    clientTableView.getColumns().addAll(firstName, lastName, phoneNumber);
                    clientTableView.setPrefSize(265, 160);
                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(clientTableView, select, close);
                    vBox.setAlignment(Pos.TOP_CENTER);
                    vBox.setFillWidth(false);
                    vBox.setSpacing(5);
                    vBox.setPrefSize(280, 236);
                    stage.setTitle("Select Client");
                    stage.setScene(new Scene(vBox));
                    stage.getIcons().add(new Image(Application.class.getResourceAsStream("icons/warehouse.png")));
                    stage.show();
                    /*vBox:[] end*/

                    select.setOnAction(e -> {
                        try {
                            if (clientTableView.getSelectionModel().getSelectedItem() != null) {
                                    clientCartDaoService = new ClientCardDaoServiceImpl();
                                    clientCartService = new ClientCartServiceImpl();
                                    var client = clientTableView.getSelectionModel().getSelectedItem();
                                    clientCartService.assignProductToClient(product, client);
                                    clientCartDaoService.addClientCart(clientCartService.getClientCart());
                                    stage.close();
                            } else {
                                AlertBox.display("Alert", "You need to select client");
                            }
                        } catch (Exception ex) {
                            AlertBox.display("Alert", ex.getMessage());
                        }
                    });
                    close.setOnAction(e -> stage.close());
                }else AlertBox.display("Error","Product is not available");
                } else AlertBox.display("Alert", "You need to select product");
            } catch(Exception ex){
                AlertBox.display("Alert", ex.getMessage());
            }
    }

    @FXML
    private void goBackBtnAction(ActionEvent event) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            AlertBox.display("Error", e.getMessage());
        }
    }


}
