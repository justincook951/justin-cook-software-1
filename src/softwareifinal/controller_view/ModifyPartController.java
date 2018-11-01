package softwareifinal.controller_view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import softwareifinal.model.Inhouse;
import softwareifinal.model.Inventory;
import softwareifinal.model.Outsourced;
import softwareifinal.model.Part;
import softwareifinal.model.Product;

/**
 *
 * @author Jay
 */
public class ModifyPartController extends PartController implements Initializable {
    
    private Part selectedPart;
    
    
    /**
     * Initialize part for modification
     * @param part 
     */
    public void initData(Part part) throws Exception {
        selectedPart = part;
        IDField.setText(((Integer)part.getPartID()).toString());
        nameField.setText(part.getName());
        invField.setText(((Integer)part.getInStock()).toString());
        priceField.setText(((Double)part.getPrice()).toString());
        minField.setText(((Integer)part.getMin()).toString());
        maxField.setText(((Integer)part.getMax()).toString());
        if (part instanceof Inhouse) {
            secondaryInputField.setText(((Integer)((Inhouse) part).getMachineID()).toString());
        }
        else if (part instanceof Outsourced) {
            secondaryInputField.setText(((Outsourced)part).getCompanyName());
        }
        else {
            throw new Exception("Data received was not a part");
        }
    }
    
    @FXML
    private void modifyPart(ActionEvent e) {
        Part part = this.buildPart();
        Inventory.updatePart(part.getPartID(), part);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        IDField.setText("");
    }
    
}
