package codychoules.application.model;

/**Class extending Part of parts made In-House with company owned machines.
 *
 * @author Cody Choules
 */
public class InHouse extends Part{

    private int machineID;

    /**
     * Constructs an Outsourced part with the given id, name, price, stock, minimum and maximum values, and machine ID.
     *
     * @param id The id of the part.
     * @param name The name of the part.
     * @param price The price of the part.
     * @param stock The current stock quantity of the part.
     * @param min The minimum stock quantity of the part.
     * @param max The maximum stock quantity of the part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * @return the machineID
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * @param machineID the machineID to set
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

}
