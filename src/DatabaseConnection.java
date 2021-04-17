import javax.xml.crypto.Data;
import java.sql.*;

public class DatabaseConnection {
    Connection conn;

    DatabaseConnection(){
        String url= "jdbc:sqlite:hospital_db.db";
        conn= null;

        try {
            conn= DriverManager.getConnection(url);
            System.out.println("Bağlandı ");
            System.out.println();
        }
        catch (SQLException e){
            System.out.println("BAĞLANAMADI");
            System.out.println(e.getMessage());
        }
    }

    //Test Amaçlı
    void testListResults() throws SQLException {

        String str= "SELECT * FROM Doctor";
        Statement statement= conn.createStatement();
        ResultSet rs= statement.executeQuery(str);

        while(rs.next()){
            System.out.println(rs.getString("name")+" ID: "+
                               rs.getInt("ID"));
        }

    }
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection= new DatabaseConnection();
        connection.testListResults();
    }
}
