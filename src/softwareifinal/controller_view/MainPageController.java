/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareifinal.controller_view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import softwareifinal.model.Inhouse;
import softwareifinal.model.Inventory;
import softwareifinal.model.Outsourced;
import softwareifinal.model.Part;
import softwareifinal.model.Product;

/**
 *
 * @author Jay
 */
public class MainPageController extends BaseController implements Initializable {
    
    private ObservableList<Product> productsWrapper;
    private ObservableList<Part> partsWrapper;
    private ArrayList<Product> productsList;
    private ArrayList<Part> partsList;
    @FXML private TableView<Part> partsTV;
    @FXML private TableColumn<Part, String> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, String> invLevelPartCol;
    @FXML private TableColumn<Part, String> pricePartCol;
    @FXML private TextField SearchParts;
    @FXML private TableView<Product> productsTV;
    @FXML private TableColumn<Part, String> productIDCol;
    @FXML private TableColumn<Part, String> productNameCol;
    @FXML private TableColumn<Part, String> invLevelProductCol;
    @FXML private TableColumn<Part, String> priceProductCol;
    @FXML private TextField SearchProducts;
    
    /*----------------------------------------
    ------------INIT FUNCTIONS----------------
    -----------------------------------------*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        this.populateViews();
        try {
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            invLevelPartCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
            pricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            
            partsTV.getItems().setAll(partsWrapper);
            
            productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
            productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            invLevelProductCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
            priceProductCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            
            productsTV.getItems().setAll(productsWrapper);
            
        }
        catch (NullPointerException e) {
            //Not a screen with a tableview -- no handling needed
        }
    }
    
    private void populateViews() {
        partsList = Inventory.getAllParts();
        partsWrapper = FXCollections.observableArrayList(
            partsList
        );
        productsList = Inventory.getAllProducts();
        productsWrapper = FXCollections.observableArrayList(
            productsList
        );
    }
    
    /*----------------------------------------
    ------------NAV FUNCTIONS-----------------
    -----------------------------------------*/
    
    @FXML
    private void changeScreen(ActionEvent e) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        try {
            String buttonId = ((Button)e.getSource()).getId();
            switch (buttonId) {
                case "addPart":
                    pane = this.loadAddPartPane(); 
                    break;
                case "modifyPart":
                    Part selectedPart = partsTV.getSelectionModel().getSelectedItem();
                    pane = this.loadModifyPartPane(selectedPart);
                    break;
                case "addProduct":
                    pane = this.loadAddProductPane();
                    break;
                case "modifyProduct":
                    Product selectedProduct = productsTV.getSelectionModel().getSelectedItem();
                    pane = this.loadModifyProductPane(selectedProduct);
                    break;
                default:
                    pane = this.loadMainPane();
                    break;
            }
        }
        catch (NullPointerException exception) {
            //Button has no class
        }
        this.switchScene(pane, e);
    }
    
    @FXML
    private void exit() {
        System.exit(0);
    }
    
    /*----------------------------------------
    ------------DELETE FUNCTIONS--------------
    -----------------------------------------*/
    
    @FXML
    protected void deletePart(ActionEvent e) {
       Part selectedPart = partsTV.getSelectionModel().getSelectedItem();
       Inventory.deletePart(selectedPart);
       try {
            changeScreen(e);
       }
       catch (IOException exception) {
           //How did you get here!?
       }
    }
    
    @FXML
    protected void deleteProduct(ActionEvent e) {
       Product selectedProduct = productsTV.getSelectionModel().getSelectedItem();
       Inventory.removeProduct(selectedProduct.getProductID());
       try {
           //Reload the screen to see the updated tables
            changeScreen(e);
       }
       catch (IOException exception) {
           //How did you get here!?
       }
    }

    
    /*----------------------------------------
    ------------MISC FUNCTIONS----------------
    -----------------------------------------*/
    
    @FXML
    private void stockTestData(ActionEvent e) throws IOException {
        Inhouse testInhouse1 = new Inhouse(Inventory.getNextPartID(), "Test Inhouse1", 1.25, 1, 1, 1, 3);
        Inventory.addPart(testInhouse1);
        Inhouse testInhouse2 = new Inhouse(Inventory.getNextPartID(), "Test Inhouse2", 2.25, 1, 1, 1, 1);
        Inventory.addPart(testInhouse2);
        Outsourced testOutsourced1 = new Outsourced(Inventory.getNextPartID(), "Test Outsourced1", 1.50, 2, 2, 4, "Parts Co");
        Inventory.addPart(testOutsourced1);
        Outsourced testOutsourced2 = new Outsourced(Inventory.getNextPartID(), "Test Outsourced2", 2.50, 3, 5, 8, "Discount Parts");
        Inventory.addPart(testOutsourced2);
        
        ArrayList<Part> partList = Inventory.getAllParts();
       
        Product test1 = new Product(Inventory.getNextProductID(), "TestProduct1", 1.50, 0, 0, 5);
        Inventory.addProduct(test1);
        Product test2 = new Product(Inventory.getNextProductID(), "TestProduct2", 2.50, 2, 0, 5);
        Inventory.addProduct(test2);
        Product test3 = new Product(Inventory.getNextProductID(), "TestProduct3", 3.50, 3, 0, 5);
        Inventory.addProduct(test3);
        Product test4 = new Product(Inventory.getNextProductID(), "TestProduct4", 4.50, 1, 0, 5);
        Inventory.addProduct(test4);
        this.changeScreen(e);
    }
    
    @FXML
    private void searchParts(ActionEvent e) {
        String searchValue = SearchParts.getText();
        if (searchValue.equals("")) {
            partsList = Inventory.getAllParts();
            partsWrapper = FXCollections.observableArrayList(
                partsList
            );
        }
        else {
            partsList = this.performPartSearch(Inventory.getAllParts(), searchValue);
            partsWrapper = FXCollections.observableArrayList(
                partsList
            );
        }
        partsTV.getItems().setAll(partsWrapper);
    }
    
    @FXML
    private void searchProducts(ActionEvent e) {
        String searchValue = SearchProducts.getText();
        if (searchValue.equals("")) {
            productsList = Inventory.getAllProducts();
            productsWrapper = FXCollections.observableArrayList(
                productsList
            );
        }
        else {
            productsList = this.performProductSearch(Inventory.getAllProducts(), searchValue);
            productsWrapper = FXCollections.observableArrayList(
                productsList
            );
        }
        productsTV.getItems().setAll(productsWrapper);
    }
    
}
