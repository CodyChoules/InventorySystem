package codychoules.application.model;

import codychoules.devtools.DevTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


/**
 * This is an additional utility class for managing parts and products.
 */
public class Inventory {

    /**
     * This is a list containing all parts.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * This is a list containing all products.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a part to the list of all parts.
     *
     * @param part The Part object to be added.
     */
    public static void addPart(Part part){
            allParts.add(part);
    }

    /**
     * Adds a Product to the list of all products.
     *
     * @param product The Product to be added.
     */
    public static void addProduct(Product product){
        allProducts.add(product);
    }

    /**
     * Searches for parts by name or ID.
     *
     * @param partialName The partial name or ID to search for
     * @return An ObservableList of found parts
     */
    public static ObservableList<Part> lookupPart(String partialName){
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
    public static ObservableList<Product> lookupProduct(String partialProductName){
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


    /**
     * Updates a part based on the index replacing it with a given part.
     *
     * @param index The index to be deleted
     * @param selectedPart The part to be inserted in its place
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.remove(index);
        allParts.add(selectedPart);
    }

    /**
     * Updates a Product based on the index replacing it with a given Product.
     *
     * @param index The index to be deleted
     * @param selectedProduct The Product to be inserted in its place
     */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.remove(index);
        allProducts.add(selectedProduct);
    }

    /**
     * Deletes part from list.
     *
     * @param selectedPart The partial name or ID to search for
     */
    public static void deletePart( Part selectedPart){
        allParts.remove(selectedPart);
    }

    /**
     * Deletes Product from list.
     *
     * @param selectedProduct The partial name or ID to search for
     */
    public static void deleteProduct( Product selectedProduct){
        allProducts.remove(selectedProduct);
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
     * Retrieves a list of all parts.
     *
     * @return The ObservableList of all parts.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
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
}
