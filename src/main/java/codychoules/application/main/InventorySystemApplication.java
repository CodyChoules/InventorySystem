package codychoules.application.main;

import codychoules.devtools.DevTool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InventorySystemApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        //assign root of scene, title, and define size.
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu-view.fxml")));
        primaryStage.setTitle("Main Menu");
        Scene scene = new Scene(root, 1000, 600);

        //Development tool to center stage on mouse cursor for ease of use in testing, Needed here before scene set & after scene size.
        DevTool.setWindowCenteredOnCursor(scene,primaryStage);

        //Set and show stage.
        primaryStage.setScene(scene);
        primaryStage.show();

        DevTool.println("Stage & Scene Set");
    }

    public static void main(String[] args) {launch();}
}
