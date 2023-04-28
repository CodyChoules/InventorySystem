package codychoules.application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
//        this.associatedPartsList = associatedPartsList;
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
     * @param part add associated part to list
     */
    public void addAssociatedParts(Part part){
        this.associatedParts.add(part);
    }

    /**
     * @param part remove associated part to list
     * @return asdfasdf
     */
    public boolean deleteAssociatedParts(Part part){
        if (this.associatedParts.contains(part)) {
            this.associatedParts.remove(part);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the list of associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

    /**
     * @param parts sets the associated parts list
     */
    public void setAllAssociatedParts(ObservableList<Part> parts){
        this.associatedParts = parts;
    }



}
