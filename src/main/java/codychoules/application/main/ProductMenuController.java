package codychoules.application.main;

import codychoules.application.model.*;
import codychoules.devtools.DevTool;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static codychoules.application.model.Inventory.getAllProductIds;

public class ProductMenuController implements Initializable {
    //!!!! why @FXML & how do I compact this into a list within another class to call upon? vv

    //FXML Declarations
    @FXML
    public Button cancelModProductButton;
    @FXML
    public RadioButton toggleProductOutsourcedButton;
    @FXML
    public RadioButton toggleProductInHouseButton;
    @FXML
    public Text errorText;
    @FXML
    public Button saveModProductButton;
    public TextField addProductIDField;
    public TextField addProductNameField;
    public TextField addProductInvField;
    public TextField addProductPriceField;
    public TextField addProductMaxField;
    public TextField addProductMinField;
    public Text addMachineIdText;
    public TextField searchPartField;
    public TableView<Part> PartTable;
    public TableColumn<Object, Object> PartIDCol;
    public TableColumn<Part, String> PartNameCol;
    public TableColumn<Part, Integer> PartStockCol;
    public TableColumn<Part, Double> PartPriceCol;
    public Button delPartButton;

    public TableView<Part> associatedPartTable;
    public TableColumn<Object, Object> associatedPartIDCol;
    public TableColumn<Part, String> associatedPartNameCol;
    public TableColumn<Part, Integer> associatedPartStockCol;
    public TableColumn<Part, Double> associatedPartPriceCol;
//    public TextField addProductMachineIDField;

    ///TODO Continue implementing associated table, now populate table with Product.associatedPart elements.
    @FXML
    public void handleCancelButton(ActionEvent actionEvent) throws IOException {
        DevTool.println("Cancel Pressed");

        //Retrieve stage & set root to Menu.
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu-view.fxml")));

        Scene modScene = new Scene(root, 1000, 600);
        stage.setTitle("Main Menu");
        stage.setScene(modScene);
        stage.show();
    }

    @FXML //handles Save when pressing enter on text feild, For ease of use.
    public void onTextFieldEnterProduct(ActionEvent actionEvent) {
        DevTool.println("Enter Pressed on Field");
        handleSaveButton(actionEvent);
    }
    @FXML  //handles Save button with saving the product into the product list
    private void handleSaveButton(ActionEvent actionEvent) {

        DevTool.println("Save Pressed");

        //Calls a product method to validate inputs, "errorText" is where the notifications will be sent.
        //TODO need to switch partTextInputCheck to a inputCheck that also works for part
        boolean check = Inventory.partTextInputCheck(
                addProductInvField,
                addProductPriceField,
                addProductMaxField,
                addProductMinField,
//                addProductMachineIDField,
                toggleProductOutsourcedButton,
                errorText);
        if (!check) {
            //Prevents invalid data from being saved
            return;
        }

        //Generates a new ID or if product is being modified, find the product  to be replaced with a matching Id.
        Product replaceProduct = null;
        int id = -1;
        //If adding id is generated.
        if (addProductIDField.getText().length() == 0){
            id = Inventory.generateUniqueId(getAllProductIds(), Inventory.nextProductId);}
        else {
            replaceProduct = Inventory.findProductWithProductId(Integer.parseInt(addProductIDField.getText()));
            DevTool.println("Found product BeingReplaced: " + replaceProduct.getName());
            id = Integer.parseInt(addProductIDField.getText());
        }


        //Retrieves values from the fields now that they have been validated
        String name = addProductNameField.getText();
        int inv = Integer.parseInt(addProductInvField.getText());
        double price = Double.parseDouble(addProductPriceField.getText());
        int max = Integer.parseInt(addProductMaxField.getText());
        int min = Integer.parseInt(addProductMinField.getText());

        //initializing child class variables, Needed for clean start for assignment
//        int machineid = -1;
//        String supplier = null;

        //Assignment of supplier or machined to inherited product object
//        if (toggleProductOutsourcedButton.isSelected()) {
//            supplier = addProductMachineIDField.getText();
//        } else {
//            machineid = Integer.parseInt(addProductMachineIDField.getText());
//        }

        //Creation of product instance and parameter placement based on type(concrete class)
        Product modingProduct;
        modingProduct = new Product(id, name, price, inv, min,  max);

        Inventory.allProducts.remove(replaceProduct);
        Inventory.allProducts.add(modingProduct);

        //Resetting error text to indicate problems are solved, For additional functionality if save does not exit window in another iteration.
        errorText.setText("");

        DevTool.printProductData(modingProduct);
    }

    int tick = 0;
    public void clickInHouseHandler(ActionEvent actionEvent) {
        DevTool.println("In-House Clicked");
        addMachineIdText.setText("Machine ID");
        tick++;
    }

    public void clickOutsourcedHandler(ActionEvent actionEvent) {
        DevTool.println("Outsourced Clicked");
        String txt = "Supplier Name";
        if (tick > 15) {
            txt = "Make up your mind!";
        }
        addMachineIdText.setText(txt);
    }

    public static Product productBeingModded = null;

    public static void passSelection(Product selection) {
        productBeingModded = selection;
    }

    //TODO Implement mod product functionality
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (productBeingModded != null) {
            DevTool.println("product" + productBeingModded.getId() + " " + productBeingModded.getName());
        }
        //Part Table Column Initialization
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartTable.setItems(Inventory.getAllParts());
        DevTool.println("Part Table Set");

        //TODO Setup Associated test data



        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        associatedPartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        associatedPartTable.setItems(Inventory.getAllAssociatedParts());
        DevTool.println("Part Table Set");
    }


    public void displayProductInFields(Product passedProduct){

        addProductIDField.setPromptText(String.valueOf(passedProduct.getId()));
        addProductIDField.setText(String.valueOf(passedProduct.getId()));
        addProductNameField.setPromptText(String.valueOf(passedProduct.getName()));
        addProductNameField.setText(String.valueOf(passedProduct.getName()));
        addProductInvField.setPromptText(String.valueOf(passedProduct.getStock()));
        addProductInvField.setText(String.valueOf(passedProduct.getStock()));
        addProductPriceField.setPromptText(String.valueOf(passedProduct.getPrice()));
        addProductPriceField.setText(String.valueOf(passedProduct.getPrice()));
        addProductMaxField.setPromptText(String.valueOf(passedProduct.getMax()));
        addProductMaxField.setText(String.valueOf(passedProduct.getMax()));
        addProductMinField.setPromptText(String.valueOf(passedProduct.getMin()));
        addProductMinField.setText(String.valueOf(passedProduct.getMin()));
//        if (passedProduct.getClass().getName().equals("InHouse")) {
//            InHouse ih = (InHouse) passedProduct;
//            addProductMachineIDField.setPromptText(String.valueOf(ih.getMachineID()));
//            addProductMachineIDField.setText(String.valueOf(ih.getMachineID()));
//        } else {
//            Outsourced os = (Outsourced) passedProduct;
//            addProductMachineIDField.setPromptText(String.valueOf(os.getCompanyName()));
//            addProductMachineIDField.setText(String.valueOf(os.getCompanyName()));
//        }
    }

    public void handleAddPartButton(ActionEvent actionEvent) {
    }

    public void handleModPartButton(ActionEvent actionEvent) {
    }

    public void handleDelPartButton(ActionEvent actionEvent) {
    }

    public void handleSearchProductButton(ActionEvent actionEvent) {
        //Using searchPartField class
        String searchFieldValue = searchPartField.getText();

        System.out.println("Searching in Parts...");

        //Creating a new parts list to display & replacing the table items with selected items
        ObservableList<Part> displayedParts = Inventory.searchByPartNameOrID(searchFieldValue);
        PartTable.setItems(displayedParts);

        System.out.println("Parts displayed");
    }
}
