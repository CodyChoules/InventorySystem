package codychoules.application.model;

import codychoules.devtools.DevTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {


    public static ObservableList<Part> allParts = FXCollections.observableArrayList();

    public static void add(Part part){
            allParts.add(part);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }




    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void add(Product product){
        allProducts.add(product);
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    private static boolean testDataInserted;
    public static void addTestData(){
        //checking if addTestData has been run before, so we don't add again on new scene load.
        if (testDataInserted) {
            return;
        }
        testDataInserted =true;

        //Creating and adding test data.
        Part part1 = new InHouse(1, "Bolt", 1, 1, 1, 2,1);
        add(part1);
        Part part2 = new Outsourced(2, "Nut", 1,  1, 1, 2, "sam's CNC");
        add(part2);
        Part part3 = new Outsourced(3, "Screw", 1,  1, 1, 2, "fire forge");
        add(part3);

        //Dev tool to check test data insert
        DevTool.println("Test data parts added:");
        for (Part item : getAllParts()) {
            DevTool.println(item.getName());
        }
//        Product Product1 = new Product(1, "Bolt Assembly", 100, 0.5);
//        ProductList.add(Product1);
//        Product Product2 = new Product(2, "Nut Assembly", 150, 0.3);
//        ProductList.add(Product2);
//        Product Product3 = new Product(3, "Screw Assembly", 200, 0.2);
//        ProductList.add(Product3);
//        System.out.println("Test data products added:");
//        for (Product items : ProductList.getTheProductList()) {
//            System.out.println(items.getProductName());
//        }
    }
}
