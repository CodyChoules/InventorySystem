package codychoules.application.main;

import codychoules.application.model.InHouse;
import codychoules.application.model.Inventory;
import codychoules.application.model.Outsourced;
import codychoules.application.model.Part;
import codychoules.devtools.DevTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static codychoules.application.model.Inventory.getAllPartIds;
import static codychoules.application.model.Inventory.getAllProductIds;

public class PartMenuController implements Initializable {
    //!!!! why @FXML & how do I compact this into a list within another class to call upon? vv

    //FXML Declarations
    @FXML
    public Button cancelModPartButton;
    @FXML
    public RadioButton togglePartOutsourcedButton;
    @FXML
    public RadioButton togglePartInHouseButton;
    @FXML
    public Text errorText;
    @FXML
    public Button saveModPartButton;
    public TextField addPartIDField;
    public TextField addPartNameField;
    public TextField addPartInvField;
    public TextField addPartPriceField;
    public TextField addPartMaxField;
    public TextField addPartMinField;
    public Text addMachineIdText;
    public TextField addPartMachineIDField;

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
    public void onTextFieldEnterPart(ActionEvent actionEvent) {
        DevTool.println("Enter Pressed on Field");
        handleSaveButton(actionEvent);
    }
    @FXML  //handles Save button with saving the part into the part list
    private void handleSaveButton(ActionEvent actionEvent) {

        DevTool.println("Save Pressed");

        //Calls a part method to validate inputs, "errorText" is where the notifications will be sent.
        boolean check = Inventory.partTextInputCheck(
                addPartInvField,
                addPartPriceField,
                addPartMaxField,
                addPartMinField,
                addPartMachineIDField,
                togglePartOutsourcedButton,
                errorText);
        if (!check) {
            //Prevents invalid data from being saved
            return;
        }

        //Retrieves values from the fields now that they have been validated
        int id = Inventory.generateUniqueId(getAllPartIds(), Inventory.nextPartId);
        String name = addPartNameField.getText();
        int inv = Integer.parseInt(addPartInvField.getText());
        double price = Double.parseDouble(addPartPriceField.getText());
        int max = Integer.parseInt(addPartMaxField.getText());
        int min = Integer.parseInt(addPartMinField.getText());

        //initializing child class variables, Needed for clean start for assignment
        int machineid = -1;
        String supplier = null;

        //Assignment of supplier or machined to inherited part object
        if (togglePartOutsourcedButton.isSelected()) {
            supplier = addPartMachineIDField.getText();
        } else {
            machineid = Integer.parseInt(addPartMachineIDField.getText());
        }

        //Creation of part instance and parameter placement based on type(concrete class)
        Part modingPart;
        if (togglePartInHouseButton.isSelected()) {
            modingPart = new InHouse(id, name, price, inv, min,  max, machineid);
        } else {
            modingPart = new Outsourced(id, name, price, inv, min, max, supplier);
        }

        Inventory.allParts.add(modingPart);

        //Resetting error text to indicate problems are solved, For additional functionality if save does not exit window in another iteration.
        errorText.setText("");

        DevTool.printPartData(modingPart);
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

    public static Part partBeingModded = null;

    public static void passSelection(Part selection) {
        partBeingModded = selection;
    }

    //TODO Implement mod part functionality
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (partBeingModded != null) {
            DevTool.println("part" + partBeingModded.getId() + " " + partBeingModded.getName());
        }
    }


    public void displayPartInFields(Part passedPart){

        addPartIDField.setPromptText(String.valueOf(passedPart.getId()));
        addPartIDField.setText(String.valueOf(passedPart.getId()));
        addPartNameField.setPromptText(String.valueOf(passedPart.getName()));
        addPartNameField.setText(String.valueOf(passedPart.getName()));
        addPartInvField.setPromptText(String.valueOf(passedPart.getStock()));
        addPartInvField.setText(String.valueOf(passedPart.getStock()));
        addPartPriceField.setPromptText(String.valueOf(passedPart.getPrice()));
        addPartPriceField.setText(String.valueOf(passedPart.getPrice()));
        addPartMaxField.setPromptText(String.valueOf(passedPart.getMax()));
        addPartMaxField.setText(String.valueOf(passedPart.getMax()));
        addPartMinField.setPromptText(String.valueOf(passedPart.getMin()));
        addPartMinField.setText(String.valueOf(passedPart.getMin()));
        if (passedPart.getClass().getName().equals("InHouse")) {
            InHouse ih = (InHouse) passedPart;
            addPartMachineIDField.setPromptText(String.valueOf(ih.getMachineID()));
            addPartMachineIDField.setText(String.valueOf(ih.getMachineID()));
        } else {
            Outsourced os = (Outsourced) passedPart;
            addPartMachineIDField.setPromptText(String.valueOf(os.getCompanyName()));
            addPartMachineIDField.setText(String.valueOf(os.getCompanyName()));
        }
    }
}
