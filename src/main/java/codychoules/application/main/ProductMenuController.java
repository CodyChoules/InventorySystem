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
/**
 * This class represents a controller for managing the product menu.
 * This can be used for both modifying and adding products.
 *
 * @author Cody Choules
 */
public class ProductMenuController implements Initializable {
    //!!!! why @FXML & how do I compact this into a list within another class to call upon? vv

    //FXML Declarations
    @FXML
    public RadioButton toggleProductOutsourcedButton;
    @FXML
    public Text errorText;
    @FXML
    public TextField addProductIDField;
    @FXML
    public TextField addProductNameField;
    @FXML
    public TextField addProductInvField;
    @FXML
    public TextField addProductPriceField;
    @FXML
    public TextField addProductMaxField;
    @FXML
    public TextField addProductMinField;


    @FXML
    public TextField searchPartField;
    @FXML
    public TableView<Part> PartTable;
    @FXML
    public TableColumn<Object, Object> PartIDCol;
    @FXML
    public TableColumn<Part, String> PartNameCol;
    @FXML
    public TableColumn<Part, Integer> PartStockCol;
    @FXML
    public TableColumn<Part, Double> PartPriceCol;
    @FXML
    public TableView<Part> associatedPartTable;
    @FXML
    public TableColumn<Object, Object> associatedPartIDCol;
    @FXML
    public TableColumn<Part, String> associatedPartNameCol;
    @FXML
    public TableColumn<Part, Integer> associatedPartStockCol;
    @FXML
    public TableColumn<Part, Double> associatedPartPriceCol;

    /**
     * Handles the cancel button action event.
     *
     * @param actionEvent The ActionEvent triggered by the cancel button.
     * @throws IOException If an error occurs during loading of the FXML file.
     */
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

    /**
     * Handles the event when the "Enter" key is pressed on a text field,
     * triggering the "Save" button functionality, for ease of use.
     *
     * @param actionEvent The ActionEvent associated with the "Enter" key press.
     * @throws IOException If an I/O error occurs during the handling of the event.
     */
    @FXML // Handles Save when pressing enter on text feild, For ease of use.
    public void onTextFieldEnterProduct(ActionEvent actionEvent) throws IOException {
        DevTool.println("Enter Pressed on Field");
        handleSaveButton(actionEvent);
    }

    /**
     * Handles Save button with saving the product into product list. This also finalizes
     * all the changes made to the product and adds or replaces the product int the product list. In addition
     * this parses and check all text fields using the Inventory.partTextInputCheck method.
     *
     * @param actionEvent The ActionEvent associated with the "Save" button press.
     * @throws IOException If an I/O error occurs during the handling of the event.
     */
    @FXML  // Handles Save button with saving the product into the product list
    private void handleSaveButton(ActionEvent actionEvent) throws IOException {

        DevTool.println("Save Pressed");

        // Calls a product method to validate inputs, "errorText" is where the notifications will be sent.
        //TODO need to switch partTextInputCheck to a inputCheck that also works for part
        boolean check = Inventory.partTextInputCheck(
                addProductNameField,
                addProductInvField,
                addProductPriceField,
                addProductMaxField,
                addProductMinField,

                toggleProductOutsourcedButton,
                errorText);
        if (!check) {
            //Prevents invalid data from being saved
            return;
        }

        // Generates a new ID or if product is being modified, find the product  to be replaced with a matching Id.
        Product replaceProduct = null;
        int id = -1;
        // If adding id is generated.
        if (addProductIDField.getText().length() == 0){
            id = Inventory.generateUniqueId(getAllProductIds(), Inventory.nextProductId);}
        else {
            replaceProduct = Inventory.findProductWithProductId(Integer.parseInt(addProductIDField.getText()));
            DevTool.println("Found product BeingReplaced: " + replaceProduct.getName());
            id = Integer.parseInt(addProductIDField.getText());
        }


        // Retrieves values from the fields now that they have been validated
        String name = addProductNameField.getText();
        int inv = Integer.parseInt(addProductInvField.getText());
        double price = Double.parseDouble(addProductPriceField.getText());
        int max = Integer.parseInt(addProductMaxField.getText());
        int min = Integer.parseInt(addProductMinField.getText());

        // Creation of product instance and parameter placement based on type(concrete class)
        Product modingProduct;
        modingProduct = new Product(id, name, price, inv, min,  max);
        modingProduct.setAssociatedPartsListIds(productBeingModdedTemp.getAllAssociatedPartIds());


        Inventory.allProducts.remove(replaceProduct);
        Inventory.allProducts.add(modingProduct);

        // Resetting error text to indicate problems are solved, For additional functionality if save does not exit window in another iteration.
        errorText.setText("");

        DevTool.printProductData(modingProduct);
        DevTool.println("Number of associated Parts: " + modingProduct.getAllAssociatedPartIds().size());


        DevTool.println("Save Pressed");

        // Retrieve stage & set root to Menu.
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu-view.fxml")));

        Scene modScene = new Scene(root, 1000, 600);
        stage.setTitle("Main Menu");
        stage.setScene(modScene);
        stage.show();
    }


    public  static  Product productBeingModded = null;
    public static Product productBeingModdedTemp = null;

    /**
     * This method handles the passing of the attributes from the selected object into this controller.
     *
     * @param selection the product provided by the Main menu selection.
     */
    public static void passSelection(Product selection) {
        productBeingModded = selection;

        //productBeingModdedTemp.setAssociatedPartsListIds(productBeingModded.getAllAssociatedPartIds());
    }


    /**
     * Initializes the view with data and sets up table columns.
     *
     * @param location  The location used to resolve relative paths for resources.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (productBeingModdedTemp != null) {
            DevTool.println("product" + productBeingModdedTemp.getId() + " " + productBeingModdedTemp.getName());
        } else {
            DevTool.println("new product being added...");
            productBeingModdedTemp = new Product(-1,null,-1, -1, -1, -1);
        }
        // Part Table Column Initialization
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartTable.setItems(Inventory.getAllParts());
        DevTool.println("Part Table Set");

        // Associated Part Table Column Initialization
        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        associatedPartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        associatedPartTable.setItems(productBeingModdedTemp.getAllAssociatedParts());//TODO Setup Associated test data
        DevTool.println("Part Table Set");
    }


    /**
     * A method to display the details of a Product object in fields.
     * This is used for modifying a Product by showing the current values of a current part.
     * @param passedProduct The Product object whose details are to be displayed.
     */
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

    /**
     * This method represents the event handler for the "Add Part" button in a JavaFX application.
     * It adds a selected part to the associated parts of a product being modified.
     *
     * @param actionEvent The ActionEvent triggered by the "Add Part" button.
     */
    public void handleAddPartButton(ActionEvent actionEvent) {
        DevTool.println("modProductClick"); // Print a message to indicate the button click.

        // Retrieve the selected part from the PartTable.
        Part select = PartTable.getSelectionModel().getSelectedItem();
        if (select == null) { // Check for null to avoid Null Exception.
            return;
        }
        int partID = select.getId(); // Retrieve the part ID.
        productBeingModdedTemp.addAssociatedPartId(partID); // Add the part ID to the associated parts of the product.

        // Set the cell values for the associated part table columns.
        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        associatedPartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        // Set the items in the associated part table to the associated parts of the product being modified.
        //productBeingModded.setAssociatedPartsListIds(productBeingModdedTemp.getAllAssociatedPartIds());
        associatedPartTable.setItems(productBeingModded.getAllAssociatedParts());

        DevTool.println("Part Table Set"); // Print a message to indicate that the associated part table has been updated.
    }

    /**
     * This method handles the remove button action event.
     * It removes the part from the temporary associated parts list.
     *
     * @param actionEvent The ActionEvent triggered by "remove" button.
     */
    public void handleDelAssociatedPartButton(ActionEvent actionEvent) {
        Part select = associatedPartTable.getSelectionModel().getSelectedItem();
        if (select == null) {
            PopupAlert.notSelectedAlert("Part");
            return;}

        boolean t = PopupAlert.conformationAlert("Parts", "Delete","Do you want to delete this part?");
        if (t) {
            Part selection = Objects.requireNonNull(select);
            productBeingModdedTemp.removeAssociated(select.getId());

            associatedPartTable.setItems(productBeingModdedTemp.getAllAssociatedParts());
        }
    }

    /**
     * This method handles the search product button in a JavaFX application then displays it int table.
     *
     * @param actionEvent The ActionEvent triggered by the search product button.
     */
    public void handleSearchProductButton(ActionEvent actionEvent) {
        // Using searchPartField class
        String searchFieldValue = searchPartField.getText();

        System.out.println("Searching in Parts...");

        // Creating a new parts list to display & replacing the table items with selected items
        ObservableList<Part> displayedParts = Inventory.searchByPartNameOrID(searchFieldValue);
        PartTable.setItems(displayedParts);

        System.out.println("Parts displayed");
    }
}
