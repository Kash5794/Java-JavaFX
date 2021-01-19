package stock.manager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import stock.manager.AddBtn.AddFile;
/**
 *
 * @author ENGR. KAZEEM SAHEED
 */
public class DatabaseHandler extends AddFile {
    
    private static DatabaseHandler handler;
    private static final String DB_URL= "jdbc:mysql://localhost:3306/stock management";
    private static Connection conn= null;
    private static Statement stmt=  null;
  
  public DatabaseHandler(){
   createConnection();
   //setupBookTable();
  }
  void createConnection() {
      
  try{
  Class.forName("com.mysql.jdbc.Driver").newInstance();
  conn = DriverManager.getConnection(DB_URL,"root","");
  }
  catch(Exception e){
  e.printStackTrace();
  }
  
  }

  
 public ResultSet execQuery(String query){
 ResultSet result;
 try{
 stmt=conn.createStatement();
 result = stmt.executeQuery(query);
 }catch(SQLException ex){
 System.out.println("Exception at execuQuery:datahandler"+ ex.getLocalizedMessage());
 
return null;
        }
 finally{}
 return result;
 }
 
 
public boolean execAction(String qu){
    
try{
stmt = conn.createStatement();
stmt.execute(qu);
return true;
}catch(SQLException ex){
 
JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
System.out.println("Exception at execuQuery:datahandler"+ ex.getLocalizedMessage());
return false;
}finally{}

} 

    
} 
