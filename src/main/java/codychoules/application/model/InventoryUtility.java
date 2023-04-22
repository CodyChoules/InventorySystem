package codychoules.application.model;

import codychoules.devtools.DevTool;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtility {


    public static int nextPartId = 1;
    public static int nextProductId = 1;


    /**
     * Performs input validation checks on text input.
     * This was designed for parts but can also be used for products.
     *
     * @param partNameField The TextField for part name input.
     * @param partInvField The TextField for part inventory input.
     * @param partPriceField The TextField for part price input.
     * @param partMaxField The TextField for part maximum inventory input.
     * @param partMinField The TextField for part minimum inventory input.
     * @param partMachineIDField   The TextField for part machine ID input.
     * @param togglePartOutsourcedButton The RadioButton for toggling between in-house and outsourced parts.
     * @param errorText The Text object for displaying error messages.
     * @return Returns true if all input validation checks pass, otherwise false.
     */
    public static boolean textFieldCheck(javafx.scene.control.TextField partNameField,
                                                javafx.scene.control.TextField partInvField,
                                                javafx.scene.control.TextField partPriceField,
                                                javafx.scene.control.TextField partMaxField,
                                                javafx.scene.control.TextField partMinField,
                                                javafx.scene.control.TextField partMachineIDField,
                                                javafx.scene.control.RadioButton togglePartOutsourcedButton,
                                                javafx.scene.text.Text errorText)
    {
        //!!!! why is this underlined
        boolean inputfail = false;
        boolean inputfailminmax = false;
        String ex = "\n";
        DevTool.println("partTextInputCheck started");
        if (partNameField.getText().trim().length() == 0) {
            DevTool.println("Name blank error");
            ex = ex.concat("No data in name field \n");
            inputfail = true;
        }

        if (partInvField.getText().trim().length() != 0) {
            try {
                Integer.parseInt(partInvField.getText().trim());
            } catch (NumberFormatException e) {
                DevTool.println("error 2 Inv");
                inputfail = true;
                ex = ex.concat("Inventory level is not an integer \n");
            }
        } else {
            DevTool.println("Inv blank error");
            ex = ex.concat("No data in Inv field \n");
            inputfail = true;
        }

        if (partPriceField.getText().trim().length() != 0) {
            try {
                Double.parseDouble(partPriceField.getText());
            } catch (NumberFormatException e) {
                DevTool.println("error 3 Price");
                inputfail = true;
                ex = ex.concat("Price is not a double \n");
            }
        } else {
            DevTool.println("Price blank error");
            ex = ex.concat("No data in Price field \n");
            inputfail = true;
        }

        if (partMaxField.getText().trim().length() != 0) {
            try {
                Double.parseDouble(partMaxField.getText());
            } catch (NumberFormatException e) {
                DevTool.println("error 4 max");
                inputfail = true;
                ex = ex.concat("Maximum inventory level is not a integer \n");
            }
        } else {
            DevTool.println("Max blank error");
            ex = ex.concat("No data in Max field \n");
            inputfail = true;
        }

        if (partMinField.getText().trim().length() != 0) {
            try {
                Double.parseDouble(partMinField.getText());
            } catch (NumberFormatException e) {
                DevTool.println("error 5 Min");
                inputfail = true;
                ex = ex.concat("Minimum inventory level is not an integer \n");
            }
        } else {
            DevTool.println("Min blank error");
            ex = ex.concat("No data in Min field \n");
            inputfail = true;
        }
        //////
        if (!inputfailminmax) {
            try {
                if (Double.parseDouble(partMinField.getText()) > Double.parseDouble(partMaxField.getText())) {
                    DevTool.println("error MIN>MAX");
                    inputfail = true;
                    ex = ex.concat("Minimum inventory level cannot be greater than maximum inventory level \n");
                }
            } catch (NumberFormatException e) {
                DevTool.println("error no min max compare");
                inputfail = true;
            }
        }
        if (!togglePartOutsourcedButton.isSelected()) {
            if (partMachineIDField.getText().trim().length() != 0) {
                try {
                    int m = Integer.parseInt(partMachineIDField.getText());
                    if (m <= 0){
                        DevTool.println("MachineId must be a positive integer");
                        inputfail = true;
                        ex = ex.concat("MachineId must be a positive integer \n");
                    }
                } catch (NumberFormatException e) {
                    DevTool.println("error 6 Machine ID");
                    inputfail = true;
                    ex = ex.concat("Machine ID is not an integer \n");
                }
            } else {
                inputfail = true;
                DevTool.println("MachineID blank error");
                ex = ex.concat("No data in MachineID field \n");
            }
        } else {
            if (partMachineIDField.getText().length() == 0) {
                inputfail = true;
                DevTool.println("Supplier Name blank error");
                ex = ex.concat("No data in Supplier Name field \n");
            }
        }

        if (inputfail) {
            errorText.setText("Exception:" + ex);
            return false;
        }
        return true;
    }

    public static boolean textFieldCheck(javafx.scene.control.TextField partNameField,
                                         javafx.scene.control.TextField partInvField,
                                         javafx.scene.control.TextField partPriceField,
                                         javafx.scene.control.TextField partMaxField,
                                         javafx.scene.control.TextField partMinField,
                                         javafx.scene.text.Text errorText)
    {
        //!!!! why is this underlined
        boolean inputfail = false;
        boolean inputfailminmax = false;
        String ex = "\n";
        DevTool.println("partTextInputCheck started");
        if (partNameField.getText().trim().length() == 0) {
            DevTool.println("Name blank error");
            ex = ex.concat("No data in name field \n");
            inputfail = true;
        }

        if (partInvField.getText().trim().length() != 0) {
            try {
                Integer.parseInt(partInvField.getText().trim());
            } catch (NumberFormatException e) {
                DevTool.println("error 2 Inv");
                inputfail = true;
                ex = ex.concat("Inventory level is not an integer \n");
            }
        } else {
            DevTool.println("Inv blank error");
            ex = ex.concat("No data in Inv field \n");
            inputfail = true;
        }

        if (partPriceField.getText().trim().length() != 0) {
            try {
                Double.parseDouble(partPriceField.getText());
            } catch (NumberFormatException e) {
                DevTool.println("error 3 Price");
                inputfail = true;
                ex = ex.concat("Price is not a double \n");
            }
        } else {
            DevTool.println("Price blank error");
            ex = ex.concat("No data in Price field \n");
            inputfail = true;
        }

        if (partMaxField.getText().trim().length() != 0) {
            try {
                Double.parseDouble(partMaxField.getText());
            } catch (NumberFormatException e) {
                DevTool.println("error 4 max");
                inputfail = true;
                ex = ex.concat("Maximum inventory level is not a integer \n");
            }
        } else {
            DevTool.println("Max blank error");
            ex = ex.concat("No data in Max field \n");
            inputfail = true;
        }

        if (partMinField.getText().trim().length() != 0) {
            try {
                Double.parseDouble(partMinField.getText());
            } catch (NumberFormatException e) {
                DevTool.println("error 5 Min");
                inputfail = true;
                ex = ex.concat("Minimum inventory level is not an integer \n");
            }
        } else {
            DevTool.println("Min blank error");
            ex = ex.concat("No data in Min field \n");
            inputfail = true;
        }
        //////
        if (!inputfailminmax) {
            try {
                if (Double.parseDouble(partMinField.getText()) > Double.parseDouble(partMaxField.getText())) {
                    DevTool.println("error MIN>MAX");
                    inputfail = true;
                    ex = ex.concat("Minimum inventory level cannot be greater than maximum inventory level \n");
                }
            } catch (NumberFormatException e) {
                DevTool.println("error no min max compare");
                inputfail = true;
            }
        }
        if (inputfail) {
            errorText.setText("Exception:" + ex);
            return false;
        }
        return true;
    }

    /**
     * Generates a unique ID by iterating from an Id counter until a unique ID is found,
     * and adds the generated ID to the provided idList.
     *
     * @param idList  List of existing IDs to avoid duplication.
     * @param nextId  The next ID to start generating from.
     * @return Generated unique ID.
     */
    public static int generateUniqueId(List<Integer> idList, int nextId) {
        int id;
        id = nextId; // Set the starting ID to the provided nextId.
        while (idList.contains(id)) { // Iterate until a unique ID is found.
            id = ++nextId; // Increment the nextId and update the ID.
        }
        idList.add(id); // Add the generated ID to the idList to avoid duplication.
        return id; // Return the generated unique ID.
    }

    /**
     * Adds test data to the inventory system.
     * Note: This method should only be called once to avoid duplicate test data.
     */
    private static boolean testDataInserted;
    public static void addTestData(){
        //checking if addTestData has been run before, so we don't add again on new scene load.
        if (testDataInserted) {
            return;
        }

        testDataInserted =true;

        //If dev tools are off no test data is entered
        if (!DevTool.toolsState()){
            return;
        }

        //Creating and adding test data for Parts.
        Part part1 = new Outsourced(generateUniqueId(Inventory.getAllPartIds(),nextPartId), "Bolt", 1, 1, 1, 2,"1");
        Inventory.addPart(part1);
        Part part2 = new Outsourced(generateUniqueId(Inventory.getAllPartIds(),nextPartId), "Nut", 1,  1, 1, 2, "sam's CNC");
        Inventory.addPart(part2);
        Part part3 = new Outsourced(generateUniqueId(Inventory.getAllPartIds(), nextPartId), "Screw", 1,  1, 1, 2, "fire forge");
        Inventory.addPart(part3);

        //Dev tool to check test data insert
        DevTool.println("Test data parts added:");
        for (Part item : Inventory.getAllParts()) {
            DevTool.println(item.getName());
        }

        // Creating and adding test data for products.
        Product Product1 = new Product(generateUniqueId(getAllProductIds(), nextProductId), "Bolt Assembly", 100.00, 5, 3,4);
        Inventory.addProduct(Product1);
        Product Product2 = new Product(generateUniqueId(getAllProductIds(), nextProductId), "Nut Assembly", 150.00, 3, 3,6);
        Inventory.addProduct(Product2);
        Product Product3 = new Product(generateUniqueId(getAllProductIds(),nextProductId), "Screw Assembly", 200.00, 2, 1,5);
        Inventory.addProduct(Product3);
        DevTool.println("Test data products added:");

        //Dev tool to check test data insert
        for (Product items : Inventory.getAllProducts()) {
            DevTool.println(items.getName());
        }

    }

    /**
     * Finds a Part object with a part ID.
     *
     * @param partId The part ID to search for.
     * @return The Part object with the given part ID, or null if not found.
     */
    public static Part findPartWithPartId(int partId) {
        Part returnedPart = null;

        for (Part part : Inventory.getAllParts()) {

            if (part.getId() == partId) {
                returnedPart = part;
            }

        }

        return returnedPart;
    }

    /**
     * Retrieves a list of all product IDs in the list of all products.
     *
     * @return A List of Integer representing all product IDs.
     */

    public static List<Integer> getAllProductIds() {
        List<Integer> ids = new ArrayList<>();
        for (Product product : Inventory.getAllProducts()) {
            ids.add(product.getId());
        }
        return ids;
    }
}
