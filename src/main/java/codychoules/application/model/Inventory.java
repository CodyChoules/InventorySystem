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
        System.out.println("Test data products added:");
        for (Product items : getAllProducts()) {
            System.out.println(items.getName());
        }
    }

    public static boolean partTextInputCheck(javafx.scene.control.TextField partIDField,
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
        System.out.println("partTextInputCheck started");

        if (partIDField.getText().length() != 0) {
            try {
                Integer.parseInt(partIDField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 1 ID");
                inputfail = true;
                ex = ex.concat("ID is not an integer \n");
            }
        } else {
            System.out.println("ID blank error");
            ex = ex.concat("No data in ID field \n");
            inputfail = true;
        }

        if (partInvField.getText().length() != 0) {
            try {
                Integer.parseInt(partInvField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 2 Inv");
                inputfail = true;
                ex = ex.concat("Inventory level is not an integer \n");
            }
        } else {
            System.out.println("Inv blank error");
            ex = ex.concat("No data in Inv field \n");
            inputfail = true;
        }

        if (partPriceField.getText().length() != 0) {
            try {
                Double.parseDouble(partPriceField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 3 Price");
                inputfail = true;
                ex = ex.concat("Price is not a double \n");
            }
        } else {
            System.out.println("Price blank error");
            ex = ex.concat("No data in Price field \n");
            inputfail = true;
        }

        if (partMaxField.getText().length() != 0) {
            try {
                Double.parseDouble(partMaxField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 4 max");
                inputfail = true;
                ex = ex.concat("Maximum inventory level is not a integer \n");
            }
        } else {
            System.out.println("Max blank error");
            ex = ex.concat("No data in Max field \n");
            inputfail = true;
        }

        if (partMinField.getText().length() != 0) {
            try {
                Double.parseDouble(partMinField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 5 Min");
                inputfail = true;
                ex = ex.concat("Minimum inventory level is not an integer \n");
            }
        } else {
            System.out.println("Min blank error");
            ex = ex.concat("No data in Min field \n");
            inputfail = true;
        }
        //////
        if (!inputfailminmax) {
            try {
                if (Double.parseDouble(partMinField.getText()) > Double.parseDouble(partMaxField.getText())) {
                    System.out.println("error MIN>MAX");
                    inputfail = true;
                    ex = ex.concat("Minimum inventory level cannot be greater than maximum inventory level \n");
                }
            } catch (NumberFormatException e) {
                System.out.println("error no min max compare");
                inputfail = true;
            }
        }
        if (!togglePartOutsourcedButton.isSelected()) {
            if (partMachineIDField.getText().length() != 0) {
                try {
                    int m = Integer.parseInt(partMachineIDField.getText());
                    if (m <= 0){
                        System.out.println("MachineId must be a positive integer");
                        inputfail = true;
                        ex = ex.concat("MachineId must be a positive integer \n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("error 6 Machine ID");
                    inputfail = true;
                    ex = ex.concat("Machine ID is not an integer \n");
                }
            } else {
                inputfail = true;
                System.out.println("MachineID blank error");
                ex = ex.concat("No data in MachineID field \n");
            }
        } else {
            if (partMachineIDField.getText().length() == 0) {
                inputfail = true;
                System.out.println("Supplier Name blank error");
                ex = ex.concat("No data in Supplier Name field \n");
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
                                             javafx.scene.control.TextField partMachineIDField,
                                             javafx.scene.control.RadioButton togglePartOutsourcedButton,
                                             javafx.scene.text.Text errorText)
    {
        //!!!! why is this underlined
        boolean inputfail = false;
        boolean inputfailminmax = false;
        String ex = "\n";
        System.out.println("partTextInputCheck started");

        if (partInvField.getText().length() != 0) {
            try {
                Integer.parseInt(partInvField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 2 Inv");
                inputfail = true;
                ex = ex.concat("Inventory level is not an integer \n");
            }
        } else {
            System.out.println("Inv blank error");
            ex = ex.concat("No data in Inv field \n");
            inputfail = true;
        }

        if (partPriceField.getText().length() != 0) {
            try {
                Double.parseDouble(partPriceField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 3 Price");
                inputfail = true;
                ex = ex.concat("Price is not a double \n");
            }
        } else {
            System.out.println("Price blank error");
            ex = ex.concat("No data in Price field \n");
            inputfail = true;
        }

        if (partMaxField.getText().length() != 0) {
            try {
                Double.parseDouble(partMaxField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 4 max");
                inputfail = true;
                ex = ex.concat("Maximum inventory level is not a integer \n");
            }
        } else {
            System.out.println("Max blank error");
            ex = ex.concat("No data in Max field \n");
            inputfail = true;
        }

        if (partMinField.getText().length() != 0) {
            try {
                Double.parseDouble(partMinField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 5 Min");
                inputfail = true;
                ex = ex.concat("Minimum inventory level is not an integer \n");
            }
        } else {
            System.out.println("Min blank error");
            ex = ex.concat("No data in Min field \n");
            inputfail = true;
        }
        //////
        if (!inputfailminmax) {
            try {
                if (Double.parseDouble(partMinField.getText()) > Double.parseDouble(partMaxField.getText())) {
                    System.out.println("error MIN>MAX");
                    inputfail = true;
                    ex = ex.concat("Minimum inventory level cannot be greater than maximum inventory level \n");
                }
            } catch (NumberFormatException e) {
                System.out.println("error no min max compare");
                inputfail = true;
            }
        }
        if (!togglePartOutsourcedButton.isSelected()) {
            if (partMachineIDField.getText().length() != 0) {
                try {
                    int m = Integer.parseInt(partMachineIDField.getText());
                    if (m <= 0){
                        System.out.println("MachineId must be a positive integer");
                        inputfail = true;
                        ex = ex.concat("MachineId must be a positive integer \n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("error 6 Machine ID");
                    inputfail = true;
                    ex = ex.concat("Machine ID is not an integer \n");
                }
            } else {
                inputfail = true;
                System.out.println("MachineID blank error");
                ex = ex.concat("No data in MachineID field \n");
            }
        } else {
            if (partMachineIDField.getText().length() == 0) {
                inputfail = true;
                System.out.println("Supplier Name blank error");
                ex = ex.concat("No data in Supplier Name field \n");
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
        System.out.println("partTextInputCheck started");

        if (partInvField.getText().length() != 0) {
            try {
                Integer.parseInt(partInvField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 2 Inv");
                inputfail = true;
                ex = ex.concat("Inventory level is not an integer \n");
            }
        } else {
            System.out.println("Inv blank error");
            ex = ex.concat("No data in Inv field \n");
            inputfail = true;
        }

        if (partPriceField.getText().length() != 0) {
            try {
                Double.parseDouble(partPriceField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 3 Price");
                inputfail = true;
                ex = ex.concat("Price is not a double \n");
            }
        } else {
            System.out.println("Price blank error");
            ex = ex.concat("No data in Price field \n");
            inputfail = true;
        }

        if (partMaxField.getText().length() != 0) {
            try {
                Double.parseDouble(partMaxField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 4 max");
                inputfail = true;
                ex = ex.concat("Maximum inventory level is not a integer \n");
            }
        } else {
            System.out.println("Max blank error");
            ex = ex.concat("No data in Max field \n");
            inputfail = true;
        }

        if (partMinField.getText().length() != 0) {
            try {
                Double.parseDouble(partMinField.getText());
            } catch (NumberFormatException e) {
                System.out.println("error 5 Min");
                inputfail = true;
                ex = ex.concat("Minimum inventory level is not an integer \n");
            }
        } else {
            System.out.println("Min blank error");
            ex = ex.concat("No data in Min field \n");
            inputfail = true;
        }
        //////
        if (!inputfailminmax) {
            try {
                if (Double.parseDouble(partMinField.getText()) > Double.parseDouble(partMaxField.getText())) {
                    System.out.println("error MIN>MAX");
                    inputfail = true;
                    ex = ex.concat("Minimum inventory level cannot be greater than maximum inventory level \n");
                }
            } catch (NumberFormatException e) {
                System.out.println("error no min max compare");
                inputfail = true;
            }
        }

        if (inputfail) {
            errorText.setText("Exception:" + ex);
            return false;
        }
        return true;
    }


}
