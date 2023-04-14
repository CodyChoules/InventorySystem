package codychoules.application.controllers;


import codychoules.devtools.DevTool;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {
    @Override  //Initialization on menu view load
    public void initialize(URL location, ResourceBundle resources) {
        DevTool.println("Control Initialization Start...");
//TODO Refactor these functions to work properly for attempt 3
        //Adding test data from TestDataClass, For dev
//        TestData.testMethod();
//        TestData.addTestData();

        //!!!! why cant I call this a function here :
        // Table.setTable();

        //Dev tool
//        for (Part item : PartList.getThePartList()) {
//            System.out.println(item.getPartName());
//        }

        //Part Table Column Initialization
//        PartIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
//        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
//        InvLevelCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inventoryLevel"));
//        CostPerPartCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("pricePerUnit"));
//        PartTable.setItems(PartList.getThePartList());
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
