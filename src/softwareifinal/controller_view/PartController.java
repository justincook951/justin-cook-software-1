/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareifinal.controller_view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import softwareifinal.model.Inhouse;
import softwareifinal.model.Inventory;
import softwareifinal.model.Outsourced;
import softwareifinal.model.Part;

/**
 *
 * @author Jay
 */
public class PartController extends BaseController {
    
    @FXML protected TextField IDField;
    @FXML protected TextField nameField;
    @FXML protected TextField invField;
    @FXML protected TextField priceField;
    @FXML protected TextField minField;
    @FXML protected TextField maxField;
    @FXML protected TextField secondaryInputField;
    @FXML protected RadioButton inhouseRadio;
    @FXML protected RadioButton outsourcedRadio;
    
    @FXML
    protected void showMachineID(ActionEvent e) {
        Label toShow = (Label)this.getSceneInfo(e).lookup("#MachineIDLabel");
        toShow.setVisible(true);
        Label toHide = (Label)this.getSceneInfo(e).lookup("#CompanyNameLabel");
        toHide.setVisible(false);
    }
    
    @FXML
    protected void showCompanyName(ActionEvent e) {
        Label toShow = (Label)this.getSceneInfo(e).lookup("#CompanyNameLabel");
        toShow.setVisible(true);
        Label toHide = (Label)this.getSceneInfo(e).lookup("#MachineIDLabel");
        toHide.setVisible(false);
    }
    
    @FXML
    protected void addPart(ActionEvent e) {
        Part part = this.buildPart();
        Inventory.addPart(part);
        try {
            this.switchScene(this.loadMainPane(), e);
        }
        catch (IOException exception) {
            
        }
    }
    
    protected Part buildPart() {
        String nameInput = nameField.getText();
        int invInput = Integer.parseInt((String)invField.getText());
        double priceInput = Double.parseDouble((String)priceField.getText());
        int minInput = Integer.parseInt((String)minField.getText());
        int maxInput = Integer.parseInt((String)maxField.getText());
        Part part;
        if (inhouseRadio.isSelected()) {
            int secondaryInput = Integer.parseInt((String)secondaryInputField.getText());
            part = new Inhouse(Inventory.getNextPartID(), nameInput, priceInput, invInput, minInput, maxInput, secondaryInput);
            if (!"Disabled - Auto Generated".equals((String)IDField.getText())) {
                part.setPartID(Integer.parseInt((String)IDField.getText()));
            }
        }
        else {
            String secondaryInput = secondaryInputField.getText();
            part = new Outsourced(Inventory.getNextPartID(), nameInput, priceInput, invInput, minInput, maxInput, secondaryInput);
            if (!"Disabled - Auto Generated".equals((String)IDField.getText())) {
                part.setPartID(Integer.parseInt((String)IDField.getText()));
            }
        }
        return part;
    }
    
}
