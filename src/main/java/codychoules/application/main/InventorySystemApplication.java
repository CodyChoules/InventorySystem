package codychoules.application.main;

import codychoules.devtools.DevTool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The main class for the Inventory System application.
 *
 * @author Cody Choules
 */
public class InventorySystemApplication extends Application {

    /**
     * The entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu-view.fxml")));
        primaryStage.setTitle("Main Menu");
        Scene scene = new Scene(root, 1000, 600);

        DevTool.setWindowCenteredOnCursor(scene,primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();

        DevTool.println("Stage & Scene Set");
    }

    /**
     * The main method for launching the Inventory System application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}