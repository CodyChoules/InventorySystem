package codychoules.application.model;

import codychoules.devtools.DevTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a utility class for managing parts and products.
 */
public class Inventory {

    /**
     * This is a list containing all parts.
     */
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Adds a part to the list of all parts.
     *
     * @param part The Part object to be added.
     */
    public static void add(Part part){
            allParts.add(part);
    }

    /**
     * Retrieves a list of all parts.
     *
     * @return The ObservableList of all parts.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Retrieves a list of all part IDs in the main list.
     *
     * @return The List of all part IDs.
     */
    public static List<Integer> getAllPartIds() {
        List<Integer> ids = new ArrayList<>();
        for (Part part : allParts) {
            ids.add(part.getId());
        }
        return ids;
    }

    /**
     * Finds a Part object with a part ID.
     *
     * @param partId The part ID to search for.
     * @return The Part object with the given part ID, or null if not found.
     */
    public static Part findPartWithPartId(int partId) {
        Part returnedPart = null;

        for (Part part : allParts) {

            if (part.getId() == partId) {
                returnedPart = part;
            }

        }

        return returnedPart;
    }

    public static int nextPartId = 1;
    public static int nextProductId = 1;

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
     * The list of all products.
     */
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a Product to the list of all products.
     *
     * @param product The Product to be added.
     */
    public static void add(Product product){
        allProducts.add(product);
    }

    /**
     * Retrieves the list of all products.
     *
     * @return The ObservableList of all products.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * Retrieves a list of all product IDs in the list of all products.
     *
     * @return A List of Integer representing all product IDs.
     */
    public static List<Integer> getAllProductIds() {
        List<Integer> ids = new ArrayList<>();
        for (Product product : allProducts) {
            ids.add(product.getId());
        }
        return ids;
    }

    /**
     * Finds a product with the given product ID.
     *
     * @param productId The product ID to search for.
     * @return The Product with the given product ID, or null if not found.
     */
    public static Product findProductWithProductId(int productId) {
        Product returnedProduct = null;

        for (Product product : allProducts) {

            if (product.getId() == productId) {
                returnedProduct = product;
            }

        }

        return returnedProduct;
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



        //Creating and adding test data for Parts.
        Part part1 = new Outsourced(generateUniqueId(getAllPartIds(),nextPartId), "Bolt", 1, 1, 1, 2,"1");
        add(part1);
        Part part2 = new Outsourced(generateUniqueId(getAllPartIds(),nextPartId), "Nut", 1,  1, 1, 2, "sam's CNC");
        add(part2);
        Part part3 = new Outsourced(generateUniqueId(getAllPartIds(), nextPartId), "Screw", 1,  1, 1, 2, "fire forge");
        add(part3);

        //Dev tool to check test data insert
        DevTool.println("Test data parts added:");
        for (Part item : getAllParts()) {
            DevTool.println(item.getName());
        }

        // Creating and adding test data for products.
        Product Product1 = new Product(generateUniqueId(getAllProductIds(), nextProductId), "Bolt Assembly", 100.00, 5, 3,4);
        allProducts.add(Product1);
        Product Product2 = new Product(generateUniqueId(getAllProductIds(), nextProductId), "Nut Assembly", 150.00, 3, 3,6);
        allProducts.add(Product2);
        Product Product3 = new Product(generateUniqueId(getAllProductIds(),nextProductId), "Screw Assembly", 200.00, 2, 1,5);
        allProducts.add(Product3);
        DevTool.println("Test data products added:");

        //Dev tool to check test data insert
        for (Product items : getAllProducts()) {
            DevTool.println(items.getName());
        }

    }


    /**
     * Performs input validation checks on text input.
     * This was designed for parts but can also be used for products.
     *
     * @param partNameField        The TextField for part name input.
     * @param partInvField         The TextField for part inventory input.
     * @param partPriceField       The TextField for part price input.
     * @param partMaxField         The TextField for part maximum inventory input.
     * @param partMinField         The TextField for part minimum inventory input.
     * @param partMachineIDField   The TextField for part machine ID input.
     * @param togglePartOutsourcedButton The RadioButton for toggling between in-house and outsourced parts.
     * @param errorText            The Text object for displaying error messages.
     * @return                     Returns true if all input validation checks pass, otherwise false.
     */
    public static boolean partTextInputCheck(javafx.scene.control.TextField partNameField,
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

    public static boolean partTextInputCheck(javafx.scene.control.TextField partNameField,
                                             javafx.scene.control.TextField partInvField,
                                             javafx.scene.control.TextField partPriceField,
                                             javafx.scene.control.TextField partMaxField,
                                             javafx.scene.control.TextField partMinField,
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
        if (inputfail) {
            errorText.setText("Exception:" + ex);
            return false;
        }
        return true;
    }

    /**
     * Searches for parts by name or ID.
     *
     * @param partialName The partial name or ID to search for
     * @return An ObservableList of found parts
     */
    public static ObservableList<Part> searchByPartNameOrID(String partialName){
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();
        partialName = partialName.toLowerCase();
        if (partialName.length() == 0){DevTool.println("Search empty, Displaying " + "All Parts");
            return allParts;
        }
        Integer partialInteger = null;
        try {
            partialInteger = Integer.parseInt(partialName);
            DevTool.println("Was able to parse integer for search = " +partialInteger);
        } catch (NumberFormatException e) {
            DevTool.println("Unable to parse integer for search on: " + partialName);
        }
        for (Part p: allParts) {
            Integer ID = p.getId();
            if (p.getName().toLowerCase().contains(partialName)
                    || ID.equals(partialInteger)) {
                foundParts.add(p);
            }
        }
        if (foundParts.size() == 0){DevTool.println("No ID or Name found for " +
                partialName);}
        return foundParts;
    }

    /**
     * Searches for products by name or ID.
     *
     * @param partialProductName The partial name or ID to search for
     * @return An ObservableList of found parts
     */
    public static ObservableList<Product> searchByProductNameOrID(String partialProductName){
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = getAllProducts();
        partialProductName = partialProductName.toLowerCase();
        if (partialProductName.length() == 0){DevTool.println("Search empty, " +
                "Displaying" +
                " " +
                "All Products");
            return allProducts;
        }
        Integer partialProductInteger = null;
        try {
            partialProductInteger = Integer.parseInt(partialProductName);
            DevTool.println("Was able to parse integer for search = " +partialProductInteger);
        } catch (NumberFormatException e) {
            DevTool.println("Unable to parse integer for search on: " + partialProductName);
        }
        for (Product p: allProducts) {
            Integer ID = p.getId();
            if (p.getName().toLowerCase().contains(partialProductName)
                    || ID.equals(partialProductInteger)) {
                foundProducts.add(p);
            }
        }
        if (foundProducts.size() == 0){DevTool.println("No ID or Name found " +
                "for " + partialProductName);}
        return foundProducts;
    }

}
