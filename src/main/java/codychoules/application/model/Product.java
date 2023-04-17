package codychoules.application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


/**
 *The Product Class, modeled after the Part Class.
 * @author Cody Choules
 */
public class Product {


    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }




    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param part add part to observable list
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    //TODO check javadocs notation after you have reviewed JavaDoc implementation.
    /**
     * @param  selectedAssociatedPart part to be deleted
     * @return true if part found
     */
    public Boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the associatedParts observableList
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public void addAssociated(Part part){
        this.associatedParts.add(part);
    }
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
    //retrieves a list of all AssociatedPartIds in main list for
    public List<Integer> getAllAssociatedPartIds() {
        List<Integer> ids = new ArrayList<>();
        for (Part part : associatedParts) {
            ids.add(part.getId());
        }
        return ids;
    }

    public Part findAssociatedPartWithPartId(int partId) {
        Part returnedPart = null;
//        boolean idDuplicates = false;
        for (Part part : associatedParts) {

            if (part.getId() == partId) {
                returnedPart = part;
            }

        }
        return returnedPart;
    }
}
