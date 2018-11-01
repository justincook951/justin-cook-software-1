/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareifinal.controller_view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import softwareifinal.model.Inventory;
import softwareifinal.model.Part;
import softwareifinal.model.Product;

/**
 *
 * @author Jay
 */
public class ModifyProductController extends ProductController {
    
    private Product selectedProduct;
    
    /**
     * Initialize product for modification
     * @param product 
     */
    public void initData(Product product) throws Exception {
        IDField.setText("");
        selectedProduct = product;
        IDField.setText(((Integer)product.getProductID()).toString());
        nameField.setText(product.getName());
        invField.setText(((Integer)product.getInStock()).toString());
        priceField.setText(((Double)product.getPrice()).toString());
        minField.setText(((Integer)product.getMin()).toString());
        maxField.setText(((Integer)product.getMax()).toString());
        productPartsList = selectedProduct.getAssociatedParts();
        ObservableList<Part> listWrapper = FXCollections.observableArrayList(productPartsList);
        productPartsTV.getItems().setAll(listWrapper);
    }
    
    @FXML
    private void modifyProduct(ActionEvent e) {
        if(this.confirmDataIsValid()) {
            Product product = this.buildNewProduct();
            Inventory.updateProduct(selectedProduct.getProductID(), product);
        }

    }
    
}
