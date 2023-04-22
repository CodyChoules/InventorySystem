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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static codychoules.application.model.Inventory.getAllPartIds;

/**
 * The PartMenuController is the controller class for the PartMenu.
 * It implements the Initialize interface for JavaFX to perform initialization tasks.
 *
 * @author Cody Choules
 */
public class PartMenuController implements Initializable {


    //FXML Declarations
    @FXML
    public RadioButton togglePartOutsourcedButton;
    @FXML
    public RadioButton togglePartInHouseButton;
    @FXML
    public Text errorText;
    @FXML
    public TextField addPartIDField;
    @FXML
    public TextField addPartNameField;
    @FXML
    public TextField addPartInvField;
    @FXML
    public TextField addPartPriceField;
    @FXML
    public TextField addPartMaxField;
    @FXML
    public TextField addPartMinField;
    @FXML
    public Text addMachineIdText;
    @FXML
    public TextField addPartMachineIDField;

    /**
     * Handles the cancel button action event.
     *
     * @param actionEvent The action event triggered by the cancel button
     * @throws IOException If an I/O error occurs during loading of the main menu view
     */
    @FXML
    public void handleCancelButton(ActionEvent actionEvent) throws IOException {
        DevTool.println("Cancel Pressed");

        //Gets window from button
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

        //Load the main menu view using the FXMLLoader
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu-view.fxml")));

        //Create a new scene with the loaded root and set it to stage
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
    @FXML
    public void onTextFieldEnterPart(ActionEvent actionEvent) throws IOException {
        DevTool.println("Enter Pressed on Field");
        handleSaveButton(actionEvent);
    }

    /**
     * Handles Save button with saving the part into the part list. This also finalizes
     * all the changes made to the part and adds or replaces the part int the part list. In addition
     * this parses and check all text fields using the Inventory.partTextInputCheck method.
     *
     * @param actionEvent The ActionEvent associated with the "Save" button press.
     * @throws IOException If an I/O error occurs during the handling of the event.
     */
    @FXML  //handles Save button with saving the part into the part list
    private void handleSaveButton(ActionEvent actionEvent) throws IOException {

        DevTool.println("Save Pressed");

        //Calls a part method to validate inputs, "errorText" is where the notifications will be sent.
        boolean check = InventoryUtility.textFieldCheck(
                addPartNameField,
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

        //Generates a new ID or if part is being modified, find the part to be replaced with a matching Id.
        Part replacePart = null;
        int replacePartIndex = -1;
        int id = -1;
        //If adding id is generated.
        if (addPartIDField.getText().length() == 0){
            id = InventoryUtility.generateUniqueId(getAllPartIds(), InventoryUtility.nextPartId);}
        else {
            replacePart = InventoryUtility.findPartWithPartId(Integer.parseInt(addPartIDField.getText()));
            replacePartIndex = Inventory.getAllParts().indexOf(replacePart);
            DevTool.println("Found partBeingReplaced: " + replacePart.getName());
            id = Integer.parseInt(addPartIDField.getText());
        }


        //Retrieves values from the fields now that they have been validated
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

        if (replacePart == null){
            Inventory.addPart(modingPart);
        } else {
            Inventory.updatePart(replacePartIndex, modingPart);
        }

        //Resetting error text to indicate problems are solved, For additional functionality if save does not exit window in another iteration.
        errorText.setText("");

        DevTool.printPartData(modingPart);

        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu-view.fxml")));

        Scene modScene = new Scene(root, 1000, 600);
        stage.setTitle("Main Menu");
        stage.setScene(modScene);
        stage.show();
    }

    int tick = 0;
    /**
     * This method handles the "In-House" button click event.
     *
     * @param actionEvent The action event triggered by the "In-House" button
     */
    public void clickInHouseHandler(ActionEvent actionEvent) {
        DevTool.println("In-House Clicked");
        addMachineIdText.setText("Machine ID");
        tick++; //Increment tick counter
    }

    /**
     * Handles the action event for the "Outsourced" toggle button click.
     *
     * @param actionEvent The action event object triggered by the button click.
     */
    public void clickOutsourcedHandler(ActionEvent actionEvent) {
        DevTool.println("Outsourced Clicked");

        String txt = "Supplier Name";
        if (tick > 15) {
            txt = "Make up your mind!"; //Something for the bunny.
        }

        //Sets the text of the "addMachineIdText" field
        addMachineIdText.setText(txt);
    }


    public static Part partBeingModded = null;

    /**
     * This method sets the currently selected 'Part' to be modified.
     * Its is used to pass the information form the Main Menu to here.
     * @param selection The 'Part' object that represents the selected part.
     */
    public static void passSelection(Part selection) {
        partBeingModded = selection;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (partBeingModded != null) {
            DevTool.println("part" + partBeingModded.getId() + " " + partBeingModded.getName());
        }
    }

    /**
     * A method to display the details of a Part object in fields.
     * This is used for modifying a part by showing the current values of a current part.
     * @param passedPart The Part object whose details are to be displayed.
     */
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
        if (passedPart instanceof InHouse ih) {
            addPartMachineIDField.setPromptText(String.valueOf(ih.getMachineID()));
            addPartMachineIDField.setText(String.valueOf(ih.getMachineID()));
        } else {
            Outsourced os = (Outsourced) passedPart;
            addPartMachineIDField.setPromptText(String.valueOf(os.getCompanyName()));
            addPartMachineIDField.setText(String.valueOf(os.getCompanyName()));
        }
    }
}
