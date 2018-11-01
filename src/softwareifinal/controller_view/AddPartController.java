package softwareifinal.controller_view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Jay
 */
public class AddPartController extends PartController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        IDField.setText("Disabled - Auto Generated");
    }

}
