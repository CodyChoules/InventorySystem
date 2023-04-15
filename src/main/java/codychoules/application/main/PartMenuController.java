package codychoules.application.main;

import codychoules.application.model.InHouse;
import codychoules.application.model.Inventory;
import codychoules.application.model.Outsourced;
import codychoules.application.model.Part;
import codychoules.devtools.DevTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class PartMenuController {
    //!!!! why @FXML & how do I compact this into a list within another class to call upon? vv

    //FXML Declarations
    @FXML
    public Button cancelModPartButton;
    @FXML
    public RadioButton togglePartOutsourcedButton;
    @FXML
    public RadioButton togglePartInHouseButton;
    @FXML
    public TextField modPartIDField;
    @FXML
    public TextField modPartNameField;
    @FXML
    public TextField modPartInvField;
    @FXML
    public TextField modPartPriceField;
    @FXML
    public TextField modPartMaxField;
    @FXML
    public TextField modPartMinField;
    @FXML
    public TextField modPartMachineIDField;
    @FXML
    public Text modMachineIdText;
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
                modPartIDField,
                modPartInvField,
                modPartPriceField,
                modPartMaxField,
                modPartMinField,
                modPartMachineIDField,
                togglePartOutsourcedButton,
                errorText);
        if (!check) {
            //Prevents invalid data from being saved
            return;
        }

        //Retrieves values from the fields now that they have been validated
        int id = Integer.parseInt(modPartIDField.getText());
        String name = modPartNameField.getText();
        int inv = Integer.parseInt(modPartInvField.getText());
        double price = Double.parseDouble(modPartPriceField.getText());
        int max = Integer.parseInt(modPartMaxField.getText());
        int min = Integer.parseInt(modPartMinField.getText());

        //machineID must be null, or we must create 2 new subclasses for Part(1 outsourced & 1 in-house)
        int machineid = 0;
        String supplier = null;

        //after setting both to null
        if (togglePartOutsourcedButton.isSelected()) {
            supplier = modPartMachineIDField.getText();
        } else {

            //vvvvvvvvvvvvvv
            machineid = Integer.parseInt(modPartMachineIDField.getText());
        }

        Part modingPart;
        if (togglePartInHouseButton.isSelected()) {
            modingPart = new InHouse(id, name, price, inv, min,  max, machineid);
        } else {
            modingPart = new Outsourced(id, name, price, inv, min, max, supplier);
        }

        Inventory.allParts.add(modingPart);
        errorText.setText("");

        DevTool.printPartData(modingPart);
    }

    int tick = 0;
    public void clickInHouseHandler(ActionEvent actionEvent) {
        DevTool.println("In-House Clicked");
        modMachineIdText.setText("Machine ID");
        tick++;
    }

    public void clickOutsourcedHandler(ActionEvent actionEvent) {
        DevTool.println("Outsourced Clicked");
        String txt = "Supplier Name";
        if (tick > 15) {
            txt = "Make up your mind!";
        }
        modMachineIdText.setText(txt);
    }

//    public static Part partBeingModded = null;
//
//    public static void passSelection(Part selection) {
//        partBeingModded = selection;
//    }

    //TODO Implement mod part functionality
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        DevTool.println("part" + partBeingModded.getId() +" "+ partBeingModded.getName());
//    }




//    private Part football = null;
//    public void displayPartInFields(Part football){
//        this.football = football;
//
//        modPartIDField.setPromptText(String.valueOf(football.getPartID()));
//        modPartIDField.setText(String.valueOf(football.getPartID()));
//        modPartNameField.setPromptText(String.valueOf(football.getPartName()));
//        modPartNameField.setText(String.valueOf(football.getPartName()));
//        modPartInvField.setPromptText(String.valueOf(football.getInventoryLevel()));
//        modPartInvField.setText(String.valueOf(football.getInventoryLevel()));
//        modPartPriceField.setPromptText(String.valueOf(football.getPricePerUnit()));
//        modPartPriceField.setText(String.valueOf(football.getPricePerUnit()));
//        modPartMaxField.setPromptText(String.valueOf(football.getMax()));////////
//        modPartMaxField.setText(String.valueOf(football.getMax()));
//        modPartMinField.setPromptText(String.valueOf(football.getMin()));
//        modPartMinField.setText(String.valueOf(football.getMin()));
//        modPartMachineIDField.setPromptText(String.valueOf(football.getMachineID()));
//        modPartMachineIDField.setText(String.valueOf(football.getMachineID()));
//
//
//    }
}
