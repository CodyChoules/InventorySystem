package codychoules.application.model;

/**Class extending Part of parts outsourced to other parties.
 *
 * @author Cody Choules
 */
public class Outsourced extends Part {


    private String companyName;

    /**
     * Constructs an Outsourced product with the given id, name, price, stock, minimum and maximum values, and company name.
     *
     * @param id The id of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The current stock quantity of the product.
     * @param min The minimum stock quantity of the product.
     * @param max The maximum stock quantity of the product.
     * @param companyName The name of the company that supplies or manufactures the product.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }


    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setMachineID(String companyName) {
        this.companyName = companyName;
    }

}
