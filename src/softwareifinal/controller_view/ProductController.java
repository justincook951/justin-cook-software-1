package softwareifinal.controller_view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import softwareifinal.model.Inventory;
import softwareifinal.model.Part;
import softwareifinal.model.Product;

/**
 *
 * @author Jay
 */
public class ProductController extends BaseController implements Initializable {
    @FXML protected ArrayList<Part> partsList;
    @FXML protected TableView<Part> partsTV;
    @FXML protected TableColumn<Part, String> partIDCol;
    @FXML protected TableColumn<Part, String> partNameCol;
    @FXML protected TableColumn<Part, String> invLevelPartCol;
    @FXML protected TableColumn<Part, String> pricePartCol;
    
    //@FXML private ObservableList<Part> productPartsList = FXCollections.observableArrayList();
    @FXML protected ArrayList<Part> productPartsList = new ArrayList<>();
    @FXML protected TableView<Part> productPartsTV;
    @FXML protected TableColumn<Part, String> productPartIDCol;
    @FXML protected TableColumn<Part, String> productPartNameCol;
    @FXML protected TableColumn<Part, String> productInvLevelPartCol;
    @FXML protected TableColumn<Part, String> productPricePartCol;
    @FXML protected TextField IDField;
    @FXML protected TextField nameField;
    @FXML protected TextField invField;
    @FXML protected TextField priceField;
    @FXML protected TextField minField;
    @FXML protected TextField maxField;
    @FXML protected TextField SearchParts;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try {            
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            invLevelPartCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
            pricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            
            partsList = Inventory.getAllParts();
            ObservableList<Part> partsWrapper = FXCollections.observableArrayList(
                partsList
            );
            partsTV.getItems().setAll(partsWrapper);
            
            productPartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
            productPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            productInvLevelPartCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
            productPricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        }
        catch (NullPointerException e) {
            //Not a screen with a tableview -- no handling needed
        }
    }
    
    @FXML
    protected void addProduct(ActionEvent e) {
        if (this.confirmDataIsValid()) {
            Product product = this.buildNewProduct();
            product.setAssociatedParts(productPartsList);
            Inventory.addProduct(product);
            this.returnHome(e);           
        }
    }
    
    @FXML
    protected void addPartToProduct(ActionEvent e) {
        Part selectedPart = partsTV.getSelectionModel().getSelectedItem();
        productPartsList.add(selectedPart);
        ObservableList<Part> listWrapper = FXCollections.observableArrayList(productPartsList);
        productPartsTV.getItems().setAll(listWrapper);
    }
    
    @FXML
    protected void removePartFromProduct() {
        Part selectedPart = productPartsTV.getSelectionModel().getSelectedItem();
        productPartsList.remove(selectedPart);
        ObservableList<Part> listWrapper = FXCollections.observableArrayList(productPartsList);
        productPartsTV.getItems().setAll(listWrapper);
    }
    
    protected boolean confirmDataIsValid() {
        //Satisfies set 2: ensuring that a product must have a name, price, and inventory level (default 0)
        boolean isValidProduct = true;
        try {
            String nameInput = nameField.getText();
            if (nameInput.equals("")) {
                throw new NumberFormatException();
            }
            int invInput = 0;
            try {
                invInput = Integer.parseInt((String)invField.getText()); 
            }
            catch (NumberFormatException exception) {
                //invInput already declared to be 0 by default.
            }
            double priceInput = Double.parseDouble((String)priceField.getText());  
        }
        catch (NumberFormatException exception) {
            isValidProduct = false;
            errorLabel.setText("You seem to be missing some data. Confirm that you have filled out all fields.");
        }
        //Satisfies set 1: ensuring that a product must always have at least one part
        if (productPartsList.isEmpty()) {
            isValidProduct = false;
            errorLabel.setText("You seem to be missing some data. Confirm that you have selected parts for this product and filled out all fields.");
        }
        return isValidProduct;
    }
    
    @FXML
    protected void searchParts(ActionEvent e) {
        String searchValue = SearchParts.getText();
        ObservableList<Part> listWrapper = FXCollections.observableArrayList();
        if (searchValue.equals("")) {
            partsList = Inventory.getAllParts();
            listWrapper = FXCollections.observableArrayList(partsList);
        }
        else {
            partsList = this.performPartSearch(Inventory.getAllParts(), searchValue);
            listWrapper = FXCollections.observableArrayList(partsList);
        }
        partsTV.getItems().setAll(listWrapper);
    }
    
    protected Product buildNewProduct() {
        String nameInput = nameField.getText();
        int invInput = 0;
        try {
            invInput = Integer.parseInt((String)invField.getText()); 
        }
        catch (NumberFormatException exception) {
            //invInput already declared to be 0 by default.
        }
        double priceInput = Double.parseDouble((String)priceField.getText());  
        int minInput = Integer.parseInt((String)minField.getText());
        int maxInput = Integer.parseInt((String)maxField.getText());
        Product product = new Product(Inventory.getNextProductID(), nameInput, priceInput, invInput, minInput, maxInput);
        if (!"Disabled - Auto Generated".equals((String)IDField.getText())) {
            product.setProductID(Integer.parseInt((String)IDField.getText()));
        }
        return product;
    }
    
}
