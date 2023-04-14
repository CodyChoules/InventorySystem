package codychoules.application.model;

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

}
