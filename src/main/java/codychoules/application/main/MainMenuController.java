package codychoules.application.main;

import codychoules.application.model.*;
import codychoules.devtools.DevTool;
import javafx.application.Platform;
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

/**
 * The controller class for the main menu view of the Inventory System application.
 *
 * @author Cody Choules
 */
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
    @FXML
    public TableView<Product> ProductTable;
    @FXML
    public TableColumn<Product, Integer> ProductIDCol;
    @FXML
    public TableColumn<Product, String> ProductNameCol;
    @FXML
    public TableColumn<Product, Integer> ProductStockCol;
    @FXML
    public TableColumn<Product, Double> ProductPriceCol;
    @FXML
    public Button searchPartButton;
    @FXML
    public Button searchProductButton;
    @FXML
    public TextField searchPartField;
    @FXML
    public TextField searchProductField;
    @FXML
    public Text productTableExceptionText;
    @FXML
    public Text partTableExceptionText;

    /**
     * Initializes the main menu view.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DevTool.println("Control Initialization Start...");

        // Adding test data
        InventoryUtility.addTestData();

        // Part Table Column Initialization
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartTable.setItems(Inventory.getAllParts());
        DevTool.println("Part Table Set");

        // Product Table Column Initialization
        ProductIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductStockCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        ProductTable.setItems(Inventory.getAllProducts());
        DevTool.println("Product Table Set");

    }
    //@@@@@@@

    /**
     * Handles the event when the "Add" button is clicked in part table.
     *
     * @param actionEvent The ActionEvent object: button click event.
     * @throws IOException If an I/O error occurs.
     */
    public void handleAddPartButton(ActionEvent actionEvent) throws IOException {
        DevTool.println("addPartClick");
        //Sets root to view,
        // loader assignment Not Needed without need to access loader methods like .getController()
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("part-menu-view.fxml")));
        //"codychoules/application/main/part-menu-view.fxml"

        //Sets stage and scene,
        // Needed to set the location, title, & size for the new scene
        Stage modStage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene modScene = new Scene(root, 1000,500);

        modStage.setTitle("Add Part Window");
        modStage.setScene(modScene);
        modStage.show();

        DevTool.println("Stage & Scene Set");
    }

    /**
     * Handles the event when the "Modify" button is clicked in part table.
     *
     * @param actionEvent The ActionEvent object representing the button click event.
     * @throws IOException If an I/O error occurs.
     */
    public void handleModPartButton(ActionEvent actionEvent) throws IOException {
        DevTool.println("modPartClick");

        //Retrieves the selected part to be modified !!!! needs NUll exception

        Part select = PartTable.getSelectionModel().getSelectedItem();
        PartMenuController.passSelection(select);
        if (select == null) {return;}
        Part selection = Objects.requireNonNull(select);


        //Sets loader to target view, loader assignment needed for exhibitB shown below.
        FXMLLoader loader = new FXMLLoader((getClass().getResource("part-menu-view.fxml")));
        Parent root = loader.load();

        DevTool.println("part selection created: ID: " + selection.getId() + " Name: " + selection.getName());
        //"codychoules/application/main/part-menu-view.fxml"

        //exhibitB: using loader to access ModifyProductController.displayPartInFields method, Needed to keep method non-static
        PartMenuController mp = loader.getController(); // must be done after "FXMLLoader.load()" method.
        mp.displayPartInFields(selection);

        //Sets stage and scene,
        // Needed to set the location, title, & size for the new scene
        Stage modStage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene modScene = new Scene(root, 1000,500);

        modStage.setTitle("Modify Part Window");
        modStage.setScene(modScene);
        modStage.show();

        DevTool.println("Stage & Scene Set");


    }

    /**
     * Handles the event when the "Delete" button is clicked in part table.
     *
     * @param actionEvent The ActionEvent object representing the button click event.
     */
    public void handleDelPartButton(ActionEvent actionEvent) {
        Part select = PartTable.getSelectionModel().getSelectedItem();
        if (select == null) {
            PopupAlert.notSelectedAlert("Part");
            return;}

        boolean t = PopupAlert.conformationAlert("Parts", "Delete","Do you want to delete this part?");
        if (t) {
            Part selection = Objects.requireNonNull(select);
            Inventory.deletePart(selection);
            PartTable.setItems(Inventory.getAllParts());
            partTableExceptionText.setText("Part (" + select.getName() + ") has been Deleted.");
        } else {
            partTableExceptionText.setText("Delete cancelled.");
        }
    }

    /**
     * Handles the search button action event for searching parts.
     *
     * @param actionEvent The ActionEvent object representing the event triggered by the search button
     */
    public void handleSearchParButton(ActionEvent actionEvent) {

        String searchFieldValue = searchPartField.getText();

        DevTool.println("Searching in Parts...");

        //Creating a new parts list to display & replacing the table items with selected items
        ObservableList<Part> displayedParts = Inventory.lookupPart(searchFieldValue);
        PartTable.setItems(displayedParts);

        DevTool.println("Parts displayed");

    }

    /**
     * Handles the event when the "Add" button is clicked in product table.
     *
     * @param actionEvent The ActionEvent object representing the event.
     * @throws IOException If an I/O exception occurs while loading the FXML file.
     */
    public void handleAddProductButton(ActionEvent actionEvent) throws IOException {
        DevTool.println("addProductClick");
        //Sets root to view,
        // loader assignment Not Needed without need to access loader methods like .getController()
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product-menu-view.fxml")));
        //"codychoules/application/main/product-menu-view.fxml"

        //Sets stage and scene,
        // Needed to set the location, title, & size for the new scene
        Stage modStage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene modScene = new Scene(root, 1000,700);

        modStage.setTitle("Add Product Window");
        modStage.setScene(modScene);
        modStage.show();

        //Part Table Column Initialization
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartTable.setItems(Inventory.getAllParts());
        DevTool.println("Part Table Set");

        DevTool.println("Stage & Scene Set");
    }

    /**
     * Handles the event when the Modify Product button is clicked.
     *
     * @param actionEvent The ActionEvent object representing the event.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void handleModProductButton(ActionEvent actionEvent) throws IOException {
        DevTool.println("modProductClick");

        //Retrieves the selected product to be modified
        Product select = ProductTable.getSelectionModel().getSelectedItem();


        // ProductMenuController.passSelection(select);
        if (select == null) {return;}
        Product selection = Objects.requireNonNull(select);


        //Sets loader to target view, loader assignment needed for exhibitB shown below.
        FXMLLoader loader = new FXMLLoader((getClass().getResource("product-menu-view.fxml")));
        Parent root = loader.load();

        DevTool.println("product selection created: ID: " + selection.getId() + " Name: " + selection.getName());

        //"codychoules/application/main/product-menu-view.fxml"

        //exhibitB: using loader to access ModifyProductController.displayProductInFields method, Needed to keep method non-static
        ProductMenuController mp = loader.getController(); // must be done after "FXMLLoader.load()" method.
        mp.passSelection(select);
        mp.displayProductInFields(selection);

        //Sets stage and scene,
        // Needed to set the location, title, & size for the new scene
        Stage modStage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene modScene = new Scene(root, 1000,700);

        modStage.setTitle("Modify Product Window");
        modStage.setScene(modScene);
        modStage.show();

        //Part Table Column Initialization
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartTable.setItems(Inventory.getAllParts());
        DevTool.println("Part Table Set");
        DevTool.println("Stage & Scene Set");

    }

    /**
     * Handles the "Delete Product" button action event.
     *
     * @param actionEvent The action event triggered by the "Delete Product" button
     */
    public void handleDelProductButton(ActionEvent actionEvent) {

        //Get the selected product from the product table
        Product select = ProductTable.getSelectionModel().getSelectedItem();
        if (select == null) {
            PopupAlert.notSelectedAlert("product");
            return;
        }

        if (select.getAllAssociatedParts().size() > 0) {
            //Show an error alert if the selected product has associated parts
            Alert alert = new Alert(Alert.AlertType.ERROR, "Associated parts are linked to this product, please modify product to have no associated parts before deleting");
            alert.setTitle("Product");
            alert.setHeaderText("Product has associated parts!");
            //return;
        }

        //Show a confirmation alert for deleting the selected product
        boolean t = PopupAlert.conformationAlert("Products", "Delete", "Do you want to delete this product?");
        if (t) {
            // Delete the selected product from the allProducts list and update the product table
            Product selection = Objects.requireNonNull(select);
            Inventory.deleteProduct(selection);
            ProductTable.setItems(Inventory.getAllProducts());
            productTableExceptionText.setText("Product (" + select.getName() + ") has been Deleted.");
        } else {
            productTableExceptionText.setText("Delete canceled.");
        }

    }

    /**
     * Handles the event when the "Search Product" button is clicked.
     *
     * @param actionEvent The action event object generated by the button click
     */
    public void handleSearchProductButton(ActionEvent actionEvent) {

        //Using searchProductField class
        String searchFieldValue = searchProductField.getText();

        DevTool.println("Searching in Products:");

        //Creating a new products list to display & replacing the table items with selected items
        ObservableList<Product> displayedProducts = Inventory.lookupProduct(searchFieldValue);
        ProductTable.setItems(displayedProducts);

        DevTool.println("Products displayed");
    }

    /**
     * Handles the event when the "Exit" button is clicked.
     *
     * @param actionEvent The action event object generated by the button click
     */
    public void handleExitButton(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
