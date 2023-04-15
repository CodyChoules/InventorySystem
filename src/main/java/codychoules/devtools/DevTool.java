package codychoules.devtools;

import codychoules.application.model.Outsourced;
import codychoules.application.model.Part;
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


    //TODO this must be able to print M.Id & S.Name
    public static void printPartData(Part part) {
        if (devToolsOn){
            System.out.println("New Part Created: " +
                part.getName() +
                " ID:" + part.getId() +
                " Inv:" + part.getStock() +
                " Price:$" + part.getPrice() +
                " Max:" + part.getMax() +
                " Min:" + part.getMin() + "\n");
//                " MachineID:" + part.getMachineID() +
//                " Supplier Name:" + part.getSupplierName());
         }
    }
}
