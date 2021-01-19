/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.charts.Legend;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stock.manager.AddBtn.WelcomeController;
import stock.manager.FrontPage.Welcome;
import stock.manager.FrontPage.WelcomeMain;

import javax.swing.*;

/**
 *
 * @author ENGR. KAZEEM SAHEED
 */
public class StockFXMLController implements Initializable {

    public JFXButton refresh;


    public javafx.scene.control.TabPane TabPane;
    public Tab tabSell;
    public JFXButton deleteBtn;
    public JFXTextField total;
    public ProgressBar progressBar;
    public JFXButton nameBtn;
    public Label nameLabel;
    ObservableList<Tabular>list=FXCollections.observableArrayList();
    //ObservableList<Tabular>list2=FXCollections.observableArrayList();
    //List<Tabular> list = new ArrayList<>();
    public AnchorPane rootpane;
    public TableView<Tabular> table;
    public TableColumn<Tabular,Integer>SNCol;
    public TableColumn<Tabular, Integer> QtyCol;
    public TableColumn<Tabular, String>nameCol;
    public TableColumn <Tabular, Integer>UPCol;
    public TableColumn <Tabular, String>DateCol;
    public JFXTextField filteredField;
    private Label label;
    @FXML
    private JFXButton addBtn;


    public int SN; public String id; public int qty; public int price;public String date;
    int cost=0;
    DatabaseHandler handler;

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            welcomeUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        initCol();
       loadData();
        //total();
        progressBar();



    }
    void welcomeUser() throws SQLException {
        String status = "true";
        handler = new DatabaseHandler();
        String qu = "SELECT* FROM users WHERE Status ='"+status+"'";
        ResultSet rs= handler.execQuery(qu);
        if(rs.next()==false){
            nameLabel.setText("Admin");
        }
        else{

                nameLabel.setText(rs.getString("Username"));
                System.out.println(rs.getString("Username"));

        }



    }

    public void filterText(){


        FilteredList<Tabular> filteredData = new FilteredList<>(list, b -> true);

        filteredField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(table -> {

                if (newValue==null || newValue.isEmpty()){
                   return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(table.getId().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }
                else if (String.valueOf(table.getPrice()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else{
                    return false;
                }



            });

        });

        SortedList<Tabular> sortedData = new SortedList<>(filteredData);
       sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(filteredData);

       // table.getItems().setAll(filteredData);


    }

    public void progressBar(){
        int level = SN;
        if (level>0 & level <100){
            progressBar.setProgress(0.1);
            progressBar.setStyle("-fx-accent: red");

        }
       else if (level>101 & level <200){
            progressBar.setProgress(0.2);
            progressBar.setStyle("-fx-accent: green");
        }
        else if (level>201 & level <300){
            progressBar.setProgress(0.3);
            progressBar.setStyle("-fx-accent: green");
        }
        else if (level>301 & level <400){
            progressBar.setProgress(0.4);
            progressBar.setStyle("-fx-accent:  #f1ce07");
        }
        else if (level>401 & level <500){
            progressBar.setProgress(0.5);
            progressBar.setStyle("-fx-accent:  #f1ce07");
        }
        else if (level>501 & level <600){
            progressBar.setProgress(0.6);
            progressBar.setStyle("-fx-accent:  #f1ce07");
        }
        else if (level>601 & level <700){
            progressBar.setProgress(0.7);
            progressBar.setStyle("-fx-accent:  #f1ce07");
        }
        else if (level>701 & level <800){
            progressBar.setProgress(0.8);
            progressBar.setStyle("-fx-accent: green");
        }
        else if (level>801 & level <900){
            progressBar.setProgress(0.9);
            progressBar.setStyle("-fx-accent: green");
        }
        else{
            progressBar.setProgress(1.0);
            progressBar.setStyle("-fx-accent: green");
        }



    }
    public void total(){
        total.setText("#"+String.valueOf(cost));

    }


    //Set the filter predicate whenever the filter changes


        // TODO

    @FXML
    private void onAddBtnClick(ActionEvent event) {

        loadWindow("/stock/manager/AddBtn/AddFile.fxml", "LOADING THE STORE");


    }
    void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(stock.manager.AddBtn.AddFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initCol() {
        SNCol.setCellValueFactory(new PropertyValueFactory<>("SN"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        UPCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        QtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    public void loadData() {

        handler=new DatabaseHandler();
        String qu= "SELECT * FROM store";
        ResultSet rs= handler.execQuery(qu);
        list.clear();
        table.setItems(list);
        try{

            while(rs.next()){
                 SN=rs.getInt("SN");
                 id=rs.getString("NAME");
                qty=rs.getInt("QUANTITY");
                price=rs.getInt("PRICE");
                date=rs.getString("DATE");
                cost+=price;
                list.add(new Tabular(SN,id,qty,price,date));


            }

        }catch(SQLException ex){
            Logger.getLogger(StockFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }


        //table.getItems().setAll(list);
        table.setItems(list);

    }

    public void onRefresh(ActionEvent actionEvent) throws SQLException {

        loadData();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Your table has been successfully updated");
        alert.showAndWait();
        total();

    }


    public void refreshOnMouse(MouseEvent mouseEvent) {


    }

    public void onDelete(ActionEvent actionEvent) {

        Tabular data =table.getSelectionModel().getSelectedItem();

        if(data==null){

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText("Kindly select the data to be deleted");
    alert.showAndWait();

    }

    else{
    Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Do you really want to delete this?");
    Optional<ButtonType> result= alert.showAndWait();
            int SN=data.getSN();

            String sql = "DELETE FROM store WHERE SN = '" + SN + "' ";
            handler = new DatabaseHandler();

    if (result.get()==ButtonType.OK){
        System.out.println(SN);
        handler.execAction(sql);
        list.remove(data);
        table.getItems().setAll(list);
    }



}

        filteredField.setDisable(false);


        }

    public void onTextfieldClicked(MouseEvent mouseEvent) {

        filterText();

        }

    public void onTextType(ActionEvent actionEvent) {


    }

    public void onLogOut(ActionEvent actionEvent) {
    }

    public void onLogout(ActionEvent actionEvent) throws SQLException {
        loadWindow("/stock/manager/FrontPage/Welcome.fxml", "LOGIN PAGE");
        rootpane.getScene().getWindow().hide();
        String status="false";
        String status2="true";
        handler = new DatabaseHandler();
        String qu = "SELECT* FROM users WHERE Status ='"+status2+"'";
        ResultSet rs= handler.execQuery(qu);
        String user = null; String pass=null;
        while (rs.next()){
            user=rs.getString("Username");
            pass=rs.getString("Password");
        }

        String q = "UPDATE users SET Status="+"'"+status+"'"+" WHERE Username ='"+user+"' AND Password = '"+pass+"' ";
         handler.execAction(q);
    }


    //Another class is starting here
public static class  Tabular{
    private final SimpleIntegerProperty SN;
    private final SimpleStringProperty id;
    private final SimpleIntegerProperty price;
    private final SimpleIntegerProperty quantity;
    private final SimpleStringProperty date;

    public Tabular(int SN, String id, int quantity, int price, String date){
        this.SN=new SimpleIntegerProperty(SN);
        this.id=new SimpleStringProperty(id);
        this.quantity=new SimpleIntegerProperty(quantity);
        this.price=new SimpleIntegerProperty(price);
        this.date=new SimpleStringProperty(date);
    }

    public int getSN() {
        return SN.get();
    }

    public String getId() {

        return id.get();
    }

    public int getQuantity() {

        return quantity.get();
    }

    public int getPrice() {

        return price.get();
    }
    public String getDate() {

        return date.get();
    }

}

}
