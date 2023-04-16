package codychoules.devtools;

import codychoules.application.model.Outsourced;
import codychoules.application.model.Part;
import codychoules.application.model.Product;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DevTool {

    private static boolean devToolsOn = true;

    public static void setWindowCenteredOnCursor(Scene scene, Stage primaryStage) {
        double mouseX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        double mouseY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        int sceneWidth = scene.widthProperty().intValue();
        int halfWidth = sceneWidth / 2;
        primaryStage.setX(mouseX - halfWidth);
        primaryStage.setY(mouseY - 10);
        System.out.print("Centered stage on mouse with: " + sceneWidth + " / 2 = " + halfWidth + " ... \n");
    }

    public static void toolsOn() {
        devToolsOn = true;
    }

    public static void toolsOff() {
        devToolsOn = false;
    }

    public static void println(String string) {
        if (devToolsOn) {
            System.out.println(string);
        }
    }


    public static void printPartData(Part part) {
        if (devToolsOn) {
            System.out.println("New Part Created: " +
                    part.getName() +
                    " ID:" + part.getId() +
                    " Inv:" + part.getStock() +
                    " Price:$" + part.getPrice() +
                    " Max:" + part.getMax() +
                    " Min:" + part.getMin() + "\n");
            //TODO this must be able to print M.Id & S.Name
//                " MachineID:" + part.getMachineID() +
//                " Supplier Name:" + part.getSupplierName());
        }
    }

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
