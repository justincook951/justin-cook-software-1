/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareifinal.controller_view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jay
 */
public class SoftwareIFinal extends Application {
    
    private static boolean is_test = true;
    
    @Override
    public void start(Stage stage) throws Exception {
      
        Parent pane = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        
        Scene scene = new Scene(pane);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
