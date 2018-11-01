package softwareifinal.controller_view;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import softwareifinal.model.Part;
import softwareifinal.model.Product;

/**
 *
 * @author Jay
 */
public class BaseController {
    
    @FXML protected Label errorLabel;
    
    private Stage getStageInfo(ActionEvent e) {
        Stage stage = (Stage)this.getSceneInfo(e).getWindow();
        return stage;
    }
    
    protected Scene getSceneInfo(ActionEvent e) {
        Scene stage = (Scene)((Node)e.getSource()).getScene();
        return stage;
    }
    
    @FXML
    protected void returnHome(ActionEvent e) {
        try {
            Parent pane = loadMainPane();
            switchScene(pane, e);
        } catch (IOException ex) {
            
        }
    }
    
    protected void switchScene(Parent pane, ActionEvent e) {
        Scene switchScene = new Scene(pane);
        Stage stage = this.getStageInfo(e);
        stage.setScene(switchScene);
    }
    
    /*----------------------------------------
    --------LOAD PARENT FUNCTIONS-------------
    -----------------------------------------*/
    
    protected Parent loadAddPartPane() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        return pane;
    }
    
    protected Parent loadModifyPartPane(Part part) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPart.fxml"));
        Parent pane = loader.load();
        
        ModifyPartController controller = loader.getController();
        try {
            controller.initData(part);
        }
        catch (Exception e) {
            //Alright, I added a try catch, Netbeans! Pls stop screaming at me!
        }
        return pane;
    }
    
    protected Parent loadAddProductPane() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        return pane;
    }
    
    protected Parent loadModifyProductPane(Product product) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        Parent pane = loader.load();
        
        ModifyProductController controller = loader.getController();
        try {
            controller.initData(product);
        }
        catch (Exception e) {
            //Alright, I added a try catch, Netbeans! Pls stop screaming at me!
        }
        return pane;
    }
    
    protected Parent loadMainPane() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        return pane;
    }
    
    protected ArrayList performPartSearch(ArrayList<Part> filterTarget, String searchTerm) {
        ArrayList<Part> returnList = new ArrayList();
        for (Part target : filterTarget) {
            String IdtoString = String.valueOf(target.getPartID());
            if (IdtoString.contains(searchTerm)) {
                returnList.add(target);
                continue;
            }
            if ((target.getName()).contains(searchTerm)) {
                returnList.add(target);
            }
        }
        return returnList;
    }
    
    protected ArrayList performProductSearch(ArrayList<Product> filterTarget, String searchTerm) {
        ArrayList<Product> returnList = new ArrayList();
        for (Product target : filterTarget) {
            String IdtoString = String.valueOf(target.getProductID());
            if (IdtoString.contains(searchTerm)) {
                returnList.add(target);
                continue;
            }
            if ((target.getName()).contains(searchTerm)) {
                returnList.add(target);
            }
        }
        return returnList;
    }
    
}
