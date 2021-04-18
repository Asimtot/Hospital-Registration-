
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.*;

public class DatabaseConnection {
    ConnectionSource conn;
    Statement statement;

    Dao<Doctor, String> doctorDao;
    Dao<Department, String> departmentDao;

    DatabaseConnection(){
        String url= "jdbc:sqlite:hospital_db.db";
        conn= null;

        try {
            conn = new JdbcConnectionSource(url);
            System.out.println("Bağlandı ");
            System.out.println();
        }
        catch (SQLException e){
            System.out.println("BAĞLANAMADI");
            System.out.println(e.getMessage());
        }
    }

    boolean connectDoctor() throws SQLException {
        doctorDao = DaoManager.createDao((ConnectionSource) conn, Doctor.class);
        return true;
    }

    boolean connectDepartment() throws SQLException{
        departmentDao= DaoManager.createDao(conn,Department.class);
        return true;
    }

    boolean addDoctor(Doctor d) throws SQLException {
        doctorDao.create(d);

        return true;
    }
    boolean addDepartment(Department d) throws SQLException{
        departmentDao.create(d);

        return true;
    }

    boolean updateDoctor(Doctor d) throws SQLException {
        doctorDao.update(d);

        return true;
    }
    
    Doctor getDoctor(int id) throws SQLException {
        return doctorDao.queryForId( (String.valueOf(id)));
    }

    Department getDepartment(int id) throws SQLException{
        return departmentDao.queryForId(String.valueOf(id));
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnection database= new DatabaseConnection();
        database.connectDoctor();
        database.connectDepartment();

        Department d= database.getDepartment(1);
        System.out.println(d.getDoctors().size());

        for(Doctor b: d.getDoctors()){
            System.out.println(b.toString());
        }

    }


}
