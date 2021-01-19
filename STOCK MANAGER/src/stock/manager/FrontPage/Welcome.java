package stock.manager.FrontPage;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import stock.manager.DatabaseHandler;
import stock.manager.StockFXMLController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Welcome implements Initializable {
    public JFXPasswordField passField;
    public JFXTextField userField;
    public Label hintText;
    public HBox hboxHint;
    public Label logginLabel;
    Stage stage;

    public Text showText;
    private Object thread;
    public String user; public String pass;
    DatabaseHandler handler;

    public void onTextMoving(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public String getTextt(){
        return showText.getText();
    }

    public void onSignup(ActionEvent actionEvent) throws IOException {
        loadWindow("/stock/manager/Registration/Registration.fxml", "REGISTRATION");
    }
    void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(stock.manager.Registration.Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void loadWindow2(String loc, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent parent = loader.load(getClass().getResource(loc));
            Stage stage = new Stage();
            stage.setTitle(title);
            Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            double width =screenSize.getWidth();
            double height = screenSize.getHeight();
            stage.setScene(new Scene(parent,width,height));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(stock.manager.Registration.Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onLogin(ActionEvent actionEvent) throws SQLException {
         user = userField.getText();
         pass= passField.getText();
         String status="true";
         handler = new DatabaseHandler();
         String qu = "SELECT* FROM users WHERE Username ='"+user+"' AND Password = '"+pass+"'";
        ResultSet rs= handler.execQuery(qu);

        String q = "UPDATE users SET Status="+"'"+status+"'"+" WHERE Username ='"+user+"' AND Password = '"+pass+"' ";

        //long mtime = System.currentTimeMillis();
        //long end= mtime+5000;

       if(user.isEmpty()==true && pass.isEmpty()==true){
            hintText.setText("All fields must be filled");
            hboxHint.setVisible(true);
        }
       else{
           if (user.equalsIgnoreCase("Admin") && pass.equalsIgnoreCase("emma123")){
               hintText.getScene().getWindow().hide();

               loadWindow2("/stock/manager/StockFXML.fxml", "STORE MANAGEMENT SYSTEM");

           }
           else if(rs.next()==true){
               hintText.getScene().getWindow().hide();
               handler.execAction(q);
               loadWindow2("/stock/manager/StockFXML.fxml", "STORE MANAGEMENT SYSTEM");
           }


           else{
               hintText.setText("Incorrect Username or Password");
               hboxHint.setVisible(true);
           }
       }




    }
}
