package stock.manager.AddBtn;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stock.manager.DatabaseHandler;
import stock.manager.FrontPage.Welcome;
import stock.manager.FrontPage.WelcomeMain;
import stock.manager.StockFXMLController;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddFile implements Initializable {
    public JFXTextField Qty;
    public JFXTextField Id;
    public JFXButton Save;
    public AnchorPane rootPane;
    public JFXTextField Price;
    public JFXButton Close;
    private URL url;
    private ResourceBundle rb;


    StockFXMLController reload = new StockFXMLController();

    //My personal variables
    DatabaseHandler handler;
    @FXML
    public void getId(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){


        // TODO
        Stage stage= new Stage();
        stage.setResizable(false);
    }
    private void loadData(){
        String  id=Id.getText();
        String  qty=Qty.getText();
        String  price=Price.getText();
        handler=new DatabaseHandler();
        Date date =new Date();
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String qu= "INSERT INTO store(NAME,QUANTITY,PRICE,DATE) VALUES("+"'"+id+"',"+"'"+qty+"',"+"'"+price+"',"+"'"+ formatter.format(date)+"'"+")";

        if(handler.execAction(qu)){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("New Item has been added successfully");
            alert.showAndWait();

    }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Something is wrong");
            alert.showAndWait();
        }
    }

    public void getQty(ActionEvent actionEvent) {
    }


    public void onSave(ActionEvent actionEvent) throws IOException, SQLException {

        loadData();

        Id.setText(" ");
        Price.setText(" ");
        Qty.setText(" ");

    }


    public void getPrice(ActionEvent actionEvent) {
    }

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage)rootPane.getScene().getWindow();
        stage.close();
    }
}
