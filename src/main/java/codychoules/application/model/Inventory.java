package codychoules.application.model;

import codychoules.devtools.DevTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Inventory {



    public static ObservableList<Part> allParts = FXCollections.observableArrayList();

    public static void add(Part part){
            allParts.add(part);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    //retrieves a list of all partIds in main list for
    public static List<Integer> getAllPartIds() {
        List<Integer> ids = new ArrayList<>();
        for (Part part : allParts) {
            ids.add(part.getId());
        }
        return ids;
    }

    public static Part findPartWithPartId(int partId) {
        Part returnedPart = null;
//        boolean idDuplicates = false;
        for (Part part : allParts) {
           //TODO implement this:
//            if (part == returnedPart) {
//                DevTool.println(returnedPart.getName() + " ");
//                idDuplicates = true;
//            }

            if (part.getId() == partId) {
                returnedPart = part;
            }

        }
//        if (idDuplicates) {
//            DevTool.println("have been found to have the same ID resulting in data integrity compromise, please remove items with matching id.");
//            return null;
//        }
        return returnedPart;
    }

    public static int nextPartId = 1;
    public static int nextProductId = 1;

    public static int generateUniqueId(List<Integer> idList, int nextId) {
        int id = 1;
        id = nextId;
        while (idList.contains(id)) {
            id = ++nextId;
        }
        idList.add(id);
        return id;
    }

    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void add(Product product){
        allProducts.add(product);
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    //retrieves a list of all partIds in main list for
    public static List<Integer> getAllProductIds() {
        List<Integer> ids = new ArrayList<>();
        for (Product product : allProducts) {
            ids.add(product.getId());
        }
        return ids;
    }

    public static Product findProductWithProductId(int productId) {
        Product returnedProduct = null;
//        boolean idDuplicates = false;
        for (Product product : allProducts) {
            //TODO implement this:
//            if (product == returnedProduct) {
//                DevTool.println(returnedProduct.getName() + " ");
//                idDuplicates = true;
//            }

            if (product.getId() == productId) {
                returnedProduct = product;
            }

        }

        return returnedProduct;
    }


    private static boolean testDataInserted;
    public static void addTestData(){
        //checking if addTestData has been run before, so we don't add again on new scene load.
        if (testDataInserted) {
            return;
        }

        testDataInserted =true;



        //Creating and adding test data.
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

        Product Product1 = new Product(generateUniqueId(getAllProductIds(), nextProductId), "Bolt Assembly", 100.00, 5, 3,4);
        allProducts.add(Product1);
        Product Product2 = new Product(generateUniqueId(getAllProductIds(), nextProductId), "Nut Assembly", 150.00, 3, 3,6);
        allProducts.add(Product2);
        Product Product3 = new Product(generateUniqueId(getAllProductIds(),nextProductId), "Screw Assembly", 200.00, 2, 1,5);
        allProducts.add(Product3);
        DevTool.println("Test data products added:");

        for (Product items : getAllProducts()) {
            DevTool.println(items.getName());
        }

    }

    public static boolean partTextInputCheckWithID(javafx.scene.control.TextField partIDField,
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

        if (partIDField.getText().trim().length() != 0) {
            try {
                Integer.parseInt(partIDField.getText());
            } catch (NumberFormatException e) {
                DevTool.println("error 1 ID");
                inputfail = true;
                ex = ex.concat("ID is not an integer \n");
            }
        } else {
            DevTool.println("ID blank error");
            ex = ex.concat("No data in ID field \n");
            inputfail = true;
        }

        if (partInvField.getText().trim().length() != 0) {
            try {
                Integer.parseInt(partInvField.getText());
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

    public static boolean partTextInputCheck(
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

        if (partInvField.getText().trim().length() != 0) {
            try {
                Integer.parseInt(partInvField.getText());
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
