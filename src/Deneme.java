import java.sql.SQLException;

public class Deneme {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        System.out.println(database.getAvailableCity("Hospital"));
    }
}
