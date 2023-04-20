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
    private List<Integer> associatedPartsList = FXCollections.observableArrayList();

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
     * @param partId add part to observable list
     */
    public void addAssociatedPartId(int partId){
        this.associatedPartsList.add(partId);
    }


    /**
     * @param  selectedAssociatedPartId part to be deleted
     * @return true if part found
     */
    public Boolean deleteAssociatedPart(int selectedAssociatedPartId){
        if (associatedPartsList.contains(selectedAssociatedPartId)) {
            associatedPartsList.remove(selectedAssociatedPartId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param associatedPartsList the associatedPartsList observableList
     */
    public void setAssociatedPartsListIds(List<Integer> associatedPartsList) {
        this.associatedPartsList = associatedPartsList;
    }

   //TODO must use add Associated over add to institiat
    public void addAssociated(int part){
        this.associatedPartsList.add(part);
    }

    /**
     * @param part the part to be removed from the associatedList
     */
    public void removeAssociated(int part){
        for (int i = 0; i <= this.associatedPartsList.toArray().length; i++){
            if (this.associatedPartsList.get(i) == part){
                this.associatedPartsList.remove(i);
                return;
            }
        }
    }

    /** Method to return the Associated parts as a list of parts opposed to a list of IDs.
     * Note: dependent on Inventory.allParts
     *
     * @return ObservableList<Part> associated part list
     */
    public ObservableList<Part> getAllAssociatedParts(){
        ObservableList<Part> partList =  FXCollections.observableArrayList();
        for (Integer partId : this.associatedPartsList) {
            for (Part part : Inventory.allParts) {
                if (part.getId() == partId){
                    partList.add(part);
                }
            }
        }
        return partList;
    }




    /** Retrieves a list of all AssociatedPartIds as a list of IDs
    *
    * @return ObservableList<Part> associated part list
    */
    public List<Integer> getAllAssociatedPartIds() {
        return associatedPartsList;
    }

//    public Part findAssociatedPartWithPartId(int partId) {
//        Part returnedPart = null;
////        boolean idDuplicates = false;
//        for (int associatedPartId: associatedPartsList) {
//            for (Part part : Inventory.allParts) {
//                if (part.getId() == partId){
//                    partList.add(part);
//                }
//            }
//
//        }
//        return returnedPart;
//    }
//
//    public Part findAssociatedPartWithPartName(int partId) {
//        Part returnedPart = null;
////        boolean idDuplicates = false;
//        for (int partId: associatedPartsList) {
//
//            if (part.getId() == partId) {
//                returnedPart = part;
//            }
//
//        }
//        return returnedPart;
//    }
}
