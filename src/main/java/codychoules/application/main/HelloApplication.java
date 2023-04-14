package codychoules.application.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX's application that displays a "hello-view.fxml" file in a window titled "Hello!".
 */
public class HelloApplication extends Application {

    /**
     * Starts the application by loading the "hello-view.fxml" file and displaying it in a new window.
     *
     * @param stage the primary stage for this application.
     * @throws IOException if the "hello-view.fxml" file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The entry point for the JavaFX application.
     *
     * @param args the command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch();
    }
}

