package codychoules.application.controllers;


import codychoules.application.model.Inventory;
import codychoules.application.model.Part;
import codychoules.devtools.DevTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {
    @FXML
    public TableView<Part> PartTable;
    @FXML
    public TableColumn<Part, Integer> PartIDCol;
    @FXML
    public TableColumn<Part, String> PartNameCol;
    @FXML
    public TableColumn<Part, Integer> PartStockCol;
    @FXML
    public TableColumn<Part, Double> PartPriceCol;


    @Override  //Initialization on menu view load
    public void initialize(URL location, ResourceBundle resources) {
        DevTool.println("Control Initialization Start...");
//TODO Refactor these functions to work properly for attempt 3
        //Adding test data from TestDataClass, For dev
//        TestData.testMethod();
//        TestData.addTestData();

        //!!!! why cant I call this a function here :
        // Table.setTable();

        //adding test data
        Inventory.addTestData();

        //Dev tool to check test data insert
        for (Part item : Inventory.getAllParts()) {
            DevTool.println( item.getName());
        }

        //Part Table Column Initialization
        PartIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
//        InvLevelCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
//        CostPerPartCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartTable.setItems(Inventory.getAllParts());
//        System.out.println("Table Set");

        //Product Table Column Initialization
//        ProductIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
//        ProductNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
//        ProdInvLvlCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inventoryLevel"));
//        CostPerProdCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("pricePerUnit"));
//        ProductTable.setItems(ProductList.getTheProductList());

    }

    public void handleAddPartButton(ActionEvent actionEvent) {
    }

    public void handleModPartButton(ActionEvent actionEvent) {
    }

    public void handleDelPartButton(ActionEvent actionEvent) {
    }

    public void handleSearchParButton(ActionEvent actionEvent) {
    }

    public void handleAddProductButton(ActionEvent actionEvent) {
    }

    public void handleModProductButton(ActionEvent actionEvent) {
    }

    public void handleDelProductButton(ActionEvent actionEvent) {
    }

    public void handleSearchProductButton(ActionEvent actionEvent) {
    }
}
