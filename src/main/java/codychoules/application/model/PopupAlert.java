package codychoules.application.model;

import codychoules.devtools.DevTool;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

/**
 * Class for displaying popup alerts.
 */
public class PopupAlert  {

    /**
     * Displays a confirmation alert dialog with the specified title, header, and description.
     *
     * @param title The title of the confirmation alert.
     * @param header The header text of the confirmation alert.
     * @param discription The description text of the confirmation alert.
     * @return  if the user clicked OK as true or if not as false.
     */
    public static boolean conformationAlert(String title, String header, String discription) {
        Alert alert = new Alert(AlertType.CONFIRMATION, discription);
        alert.setTitle(title);
        alert.setHeaderText(header);
        Optional<ButtonType> result = alert.showAndWait();


        return result.isPresent() && result.get() == ButtonType.OK;

    }

    /**
     * Displays a warning alert dialog indicating that a target must be selected.
     *
     * @param target The target type that must be selected.
     */
    public static void notSelectedAlert(String target) {
        Alert alert = new Alert(AlertType.NONE, "test");

        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("A " + target + " must be selected.");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();

    }


}
