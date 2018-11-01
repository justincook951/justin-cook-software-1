package softwareifinal.model;

/**
 *
 * @author Jay
 */
public class Inhouse extends Part 
{
    
    protected int machineID;
    
    public Inhouse(int ID, String name, double price, int stock, int min, int max, int machineID) { 
        this.partID = ID;
        this.name = name;
        this.price = price;
        this.inStock = stock;
        this.min = min;
        this.max = max;
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
}
