package codychoules.application.model;

import codychoules.devtools.DevTool;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

public class PopupAlert  {

//    /**
//     * Constructor for creating a warning pop-up dialog with a customizable message.
//     *
//     * @param message The warning message to be displayed in the dialog.
//     */
//    public Popup(String message) {
//        super(AlertType.CONFIRMATION);
//        setTitle("Warning");
//        setHeaderText(null);
//        setContentText(message);
//        getButtonTypes().setAll(ButtonType.OK);
//    }
//
//    public static
//    /**
//     * Method to show the warning pop-up dialog.
//     */
//    public void showPopup() {
//        this.showAndWait();
//    }

    public static boolean conformationAlert(String title, String header, String discription) {
        Alert alert = new Alert(AlertType.CONFIRMATION, discription);
        alert.setTitle(title);
        alert.setHeaderText(header);
        Optional<ButtonType> result = alert.showAndWait();


        return result.isPresent() && result.get() == ButtonType.OK;

    }
//Implementation:
//        boolean t = Popup.testAlert();
//        if (t) {
//            DevTool.println("Replace this print with the feature you want done on OK");
//        }

    public static void notSelectedAlert(String target) {
        Alert alert = new Alert(AlertType.NONE, "test");

        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("A " + target + " must be selected.");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();

    }


}
