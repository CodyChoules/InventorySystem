package codychoules.application.main;


import codychoules.application.model.*;
import codychoules.devtools.DevTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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


    @Override  //Initialization on menu view load
    public void initialize(URL location, ResourceBundle resources) {
        DevTool.println("Control Initialization Start...");

        //adding test data
        Inventory.addTestData();

        //Part Table Column Initialization
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartTable.setItems(Inventory.getAllParts());
        DevTool.println("Part Table Set");

        //Product Table Column Initialization
        ProductIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductStockCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        ProductTable.setItems(Inventory.getAllProducts());
        DevTool.println("Product Table Set");

    }

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
