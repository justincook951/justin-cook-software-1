package softwareifinal.model;

/**
 *
 * @author Jay
 */
public class Outsourced extends Part
{
    
    protected String companyName;
    
    public Outsourced(int ID, String name, double price, int stock, int min, int max, String companyName) { 
        this.partID = ID;
        this.name = name;
        this.price = price;
        this.inStock = stock;
        this.min = min;
        this.max = max;
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
            
}
