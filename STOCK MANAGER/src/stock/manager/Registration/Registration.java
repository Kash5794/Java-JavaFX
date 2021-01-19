package stock.manager.Registration;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stock.manager.DatabaseHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Registration implements Initializable {

    public JFXTextField userTextfield;
    public JFXTextField Password;
    public JFXTextField ConfirmPassword;
    public JFXTextField phoneNo;
    public AnchorPane rootPane;
    DatabaseHandler handler;
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = new Stage();
        stage.setResizable(false);
    }

    public void onSubmit(ActionEvent actionEvent) {
        handler = new DatabaseHandler();
        String user = userTextfield.getText();
        String pass= Password.getText();
        String cPass= ConfirmPassword.getText();
        String phone = phoneNo.getText();


        String qu = "INSERT INTO Users(Username,Password,Phone,Status) VALUES("+"'"+user+"',"+"'"+pass+"',"+"'"+phone+"',"+"'"+false+"'"+")";

        if(handler.execAction(qu)){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Account created successfully");
            alert.showAndWait();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please try again later, database is currently busy");
            alert.showAndWait();
        }
    }

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage)rootPane.getScene().getWindow();
        stage.close();
    }
}
