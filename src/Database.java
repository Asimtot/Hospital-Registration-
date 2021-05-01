
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import Person.*;
import Schedule.*;
import GeneralInfo.*;

public class Database {

    private Configuration configuration;
    private SessionFactory factory;
    private Session session;
    private Connection connection;

    Database(){
        try {
            configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(Medication.class);
            configuration.addAnnotatedClass(Disease.class);
            configuration.addAnnotatedClass(Prescription.class);
            configuration.addAnnotatedClass(Consultation.class);
            configuration.addAnnotatedClass(BodyPart.class);
            configuration.addAnnotatedClass(Body.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(GeneralInfo.class);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Notification.class);
            configuration.addAnnotatedClass(Admin.class);
            configuration.addAnnotatedClass(Department.class);
            configuration.addAnnotatedClass(Doctor.class);
            configuration.addAnnotatedClass(Hospital.class);
            configuration.addAnnotatedClass(Patient.class);
            configuration.addAnnotatedClass(Task.class);
            configuration.addAnnotatedClass(Appointment.class);
            configuration.addAnnotatedClass(DailySchedule.class);
            configuration.addAnnotatedClass(Schedule.class);

            basicConnection();

            factory= configuration.buildSessionFactory();



            System.out.println("Bağlandı");
        }
        catch (Exception e){
            System.out.println("Bağlanamadı");
            System.out.println(e.getMessage());
        }

    }
    public void basicConnection(){
        String url= "jdbc:mysql://139.177.194.75:3306/mydb";
        String userName= "hospital_user";
        String password= "a11B22c33D44-";

        try{
            connection= DriverManager.getConnection(url, userName, password);
            
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Basic connection failed");
        }
    }

    /**
    *Sadece name olanlarda kullanın
    * hem name hem surnamei olanlarda name uyuşan ilk idyi verir
    */
    int getIdByName(String name,String objectName) throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT id FROM "+objectName+" WHERE name="+"'"+name+"';";
        System.out.println(sql);

        ResultSet rs= statement.executeQuery(sql);

        while(rs.next()){
            statement.close();
            String sId= rs.getString("id");
            return Integer.parseInt(sId);
        }
        statement.close();
        return -1;
        
    }
    int getIdByNameSurname(String name,String surname ,String objectName) throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT id From "+objectName+" WHERE name="+name+ " AND surname="+surname;

        ResultSet rs= statement.executeQuery(sql);

        
        while(rs.next()){
            statement.close();
            String sId= rs.getString("id");
            return Integer.parseInt(sId);
        }
        statement.close();
        return -1;
    }
    String getPassword(String mail) throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT password FROM Person WHERE email= '"+mail+"';";

        ResultSet rs= statement.executeQuery(sql);

        while(rs.next()){
            
            String pswd= rs.getString("email");
            statement.close();
            return pswd;
        }
        statement.close();
        return "";
    }

    boolean add(Object o){
        session= factory.getCurrentSession();
        session.beginTransaction();

        session.save(o);
        
        
        session.getTransaction().commit();
        session.close();

        return true;
    }

    boolean update(Object o){
        
        session= factory.getCurrentSession();
        session.beginTransaction();

        session.update(o);
        
        
        session.getTransaction().commit();
        session.close();

        return true;
    }

    public Address getAddress(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Address a= session.get(Address.class, i);
        session.getTransaction().commit();
        session.close();
        return a;
    }

    public Body getBody(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Body b= session.get(Body.class, i);
        session.getTransaction().commit();
        session.close();
        return b;
    }
        

    public BodyPart getBodyPart(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        BodyPart bp= session.get(BodyPart.class, i);
        session.getTransaction().commit();
        session.close();

        return bp; 
    }

    public Consultation getConsultation(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Consultation c= session.get(Consultation.class, i);
        session.getTransaction().commit();
        session.close();
        return c;
    }

    public Disease getDisease(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Disease d= session.get(Disease.class, i);
        session.getTransaction().commit();
        session.close();
        return d;
    }


    public GeneralInfo getGeneralInfo(int i){
        return session.get(GeneralInfo.class, i);
    }

    public Notification getNotification(int i){
        return session.get(Notification.class, i);
    }

    public Medication getMedication(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Medication m= session.get(Medication.class,i);
        session.getTransaction().commit();
        session.close();
        return  m;
    }

    public Prescription getPrescription(int i){
        return session.get(Prescription.class, i);
    }

    public Admin getAdmin(int i){
        return session.get(Admin.class, i);
    }

    public Department getDepartment(int i){
        return session.get(Department.class, i);
    }

    public Doctor getDoctor(int i){
        return session.get(Doctor.class, i);
    }

    public Hospital getHospital(int i){
        return session.get(Hospital.class, i);
    }

    public Patient getPatient(int i){
        return session.get(Patient.class, i);
    }

    public Person getPerson(int i){
        return session.get(Person.class, i);
    }

    public Task getTask(int i){
        return session.get(Task.class, i);
    }

    public Appointment getAppointment(int i){
        return session.get(Appointment.class, i);
    }

    public DailySchedule getDailySchedule(int i){
        return session.get(DailySchedule.class, i);
    }

    public Schedule getSchedule(int i){
        return session.get(Schedule.class, i);
    }





    public static void main(String[] args) throws SQLException {
        Database database= new Database();

        System.out.println(database.getIdByName("Cance", "Disease"));
 
    }
}
