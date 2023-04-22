package codychoules.devtools;

import codychoules.application.model.Outsourced;
import codychoules.application.model.Part;
import codychoules.application.model.Product;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is a class for development tools.
 */
public class DevTool {

    /**
     * Boolean to turn off or on DevTool functions
     */
    private static boolean devToolsOn = true;

    /**
     * Sets the window centered on the cursor.
     *
     * @param scene The JavaFX Scene object
     * @param primaryStage The JavaFX Stage object
     */
    public static void setWindowCenteredOnCursor(Scene scene, Stage primaryStage) {
        double mouseX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        double mouseY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        int sceneWidth = scene.widthProperty().intValue();
        int halfWidth = sceneWidth / 2;
        primaryStage.setX(mouseX - halfWidth);
        primaryStage.setY(mouseY - 10);
        System.out.print("Centered stage on mouse with: " + sceneWidth + " / 2 = " + halfWidth + " ... \n");
    }

    /**
     * Turns on the developer tools.
     */
    public static void toolsOn() {
        devToolsOn = true;
    }

    /**
     * Turns off the developer tools.
     */
    public static void toolsOff() {
        devToolsOn = false;
    }

    /**
     * gets state of developer tools.
     */
    public static boolean toolsState() {
        return devToolsOn;
    }

    /**
     * Prints the given string to the console, but only if developer tools are enabled.
     *
     * @param string The string to be printed.
     */
    public static void println(String string) {
        if (devToolsOn) {
            System.out.println(string);
        }
    }

    /**
     * Prints the details of a Part if devToolsOn is enabled.
     *
     * @param part The Part object to print the details of.
     */
    public static void printPartData(Part part) {
        if (devToolsOn) {
            System.out.println("New Part Created: " +
                    part.getName() +
                    " ID:" + part.getId() +
                    " Inv:" + part.getStock() +
                    " Price:$" + part.getPrice() +
                    " Max:" + part.getMax() +
                    " Min:" + part.getMin() + "\n");
        }
    }

    /**
     * Prints the details of a Product if devToolsOn is enabled.
     *
     * @param product The Part object to print the details of.
     */
    public static void printProductData(Product product) {
        if (devToolsOn) {
            System.out.println("New Part Created: " +
                    product.getName() +
                    " ID:" + product.getId() +
                    " Inv:" + product.getStock() +
                    " Price:$" + product.getPrice() +
                    " Max:" + product.getMax() +
                    " Min:" + product.getMin() + "\n");
        }
    }
}
