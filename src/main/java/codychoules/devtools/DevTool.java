package codychoules.devtools;

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


    //TODO this must be reimplemented for original part
//    public static void printPartData(Part part) {
//        if (devToolsOn){
//        System.out.println("New Part Created: " +
//                part.getPartName() +
//                " ID:" + part.getPartID() +
//                " Inv:" + part.getInventoryLevel() +
//                " Price:$" + part.getPricePerUnit() +
//                " Max:" + part.getMax() +
//                " Min:" + part.getMin() +
//                " MachineID:" + part.getMachineID() +
//                " Supplier Name:" + part.getSupplierName());
//         }
//    }
}
