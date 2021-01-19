/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stock.manager.FrontPage.Welcome;
import stock.manager.FrontPage.WelcomeMain;

import java.awt.*;

/**
 *
 * @author ENGR. KAZEEM SAHEED
 */
public class STOCKMANAGER extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/stock/manager/StockFXML.fxml"));
        Parent root = loader.load();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width =screenSize.getWidth();
        double height = screenSize.getHeight();

        Scene scene = new Scene(root,width,height);
        
        stage.setScene(scene);

        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(STOCKMANAGER.class, args);
    }
    
}
