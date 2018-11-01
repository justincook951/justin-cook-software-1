package softwareifinal.model;

import java.util.ArrayList;

/**
 *
 * @author Jay
 */
public class Inventory 
{
    
    private static int productsId = 0;
    private static int partsId = 0;
    private static ArrayList<Product> allProducts = new ArrayList<Product>();
    private static ArrayList<Part> allParts = new ArrayList<Part>();

    /*----------------------------------------
    ------------PART FUNCTIONS----------------
    -----------------------------------------*/
    
    public static ArrayList<Part> getAllParts() {
        return allParts;
    }
    
    public static void addPart(Part part) {
        allParts.add(part);
        partsId++;
    }
    
    public static boolean deletePart(Part part) { 
        return allParts.remove(part);
    }
    
    public static Part lookupPart(int partID) {
        //Yes, I inserted the "lookup by ID" functionality into the standard search criteria.
        //I'm not sure why I would use this when I can use that for both purposes?
        return null;
    }
    
    public static void updatePart(int partID, Part inbPart) {
        //Casting between part types isn't worth the hassle
        removeProduct(partID);
        addPart(inbPart);
    }
    
    public static int getNextPartID() {
        return partsId;
    }
    
    /*----------------------------------------
    ------------PRODUCT FUNCTIONS-------------
    -----------------------------------------*/
    
    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }
    
    public static void addProduct(Product product) {
        allProducts.add(product);
        productsId++;
    }
    
    public static boolean removeProduct(int productID) { 
        for (int i=0;i<allProducts.size();i++) {
            if (allProducts.get(i).getProductID() == productID) {
                allProducts.remove(i);
                break;
            }
        }
        //Convert to boolean? Don't think so.
        return true;
    }
    
    public static Product lookupProduct(int productID) {
        //Yes, I inserted the "lookup by ID" functionality into the standard search criteria.
        //I'm not sure why I would use this when I can use that for both purposes?
        return null;
    }
    
    public static void updateProduct(int productID, Product inbProduct) {
        //To maintain consistency with the methods behind Part updates
        removeProduct(productID);
        addProduct(inbProduct);
    }
    
    public static int getNextProductID() {
        return productsId;
    }
    
}
