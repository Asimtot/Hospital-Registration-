
import java.sql.*;

public class DatabaseConnection {
    static Connection conn;
    static Statement statement;

    DatabaseConnection(){
        String url= "jdbc:sqlite:hospital_db.db";
        conn= null;

        try {
            conn = DriverManager.getConnection(url);
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
        statement= conn.createStatement();
        ResultSet rs= statement.executeQuery(str);

        while(rs.next()){
            System.out.println(rs.getString("name")+" ID: "+
                               rs.getInt("ID"));
        }

    }
    //Test Amaçlı
    //BOZUK
    void testListResultsTwo() throws SQLException {

        String str= "SELECT * FROM Doctor";
        statement= conn.createStatement();
        ResultSet rs= statement.executeQuery(str);

        while(rs.next()){
            System.out.println(rs.getString("name"));
        }

    }

    boolean addDoctor(Doctor d) throws SQLException {
        int ID= d.getID();
        String name= "'"+d.getName()+"'";
        String surname= "'"+d.getSurname()+"'";

        String str= "INSERT INTO Doctor VALUES ("+ID+","+name+","+surname+")";
        System.out.println(str);
       //statement= conn.createStatement();
        statement.executeUpdate(str);
        return true;

    }
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        connection.testListResultsTwo();
        connection.testListResults();
    }


}
