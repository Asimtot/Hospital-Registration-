
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public int getIdByName(String name,String objectName) throws SQLException{
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
    public int getIdByNameSurname(String name,String surname ,String objectName) throws SQLException{
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
    public String getPassword(String mail) throws SQLException{
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
    
    public ArrayList<String> getAvailableCity(String objectName) throws SQLException{
        Statement statement= connection.createStatement();
        
        String sql= "SELECT Address.city FROM "+objectName+" JOIN Address ON "+objectName+".Address_id = Address.id;" ;
        System.out.println(sql);

        ResultSet rs= statement.executeQuery(sql);
        ArrayList<String> citys= new ArrayList<>();

        while(rs.next()){
            citys.add(rs.getString("city"));
        }
        statement.close();
        
        return (ArrayList<String>)citys.stream().distinct().collect(Collectors.toList());

    }
    public ArrayList<String> getAvailableCounty(String objectName,String city) throws SQLException{

        Statement statement= connection.createStatement();

        String sql= "SELECT Address.county FROM "+objectName+" JOIN Address ON "+objectName+".Address_id = Address.id WHERE Address.city= '"+city+"';" ;
        System.out.println(sql);

        ResultSet rs= statement.executeQuery(sql);
        ArrayList<String> countys= new ArrayList<>();

        while(rs.next()){
            countys.add(rs.getString("county"));
        }
        statement.close();
        
        return (ArrayList<String>)countys.stream().distinct().collect(Collectors.toList());

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

    public Disease getDisease(String name) throws SQLException{
        session= factory.getCurrentSession();
        session.beginTransaction();
        Disease d= session.get(Disease.class, getIdByName(name,"Disease"));
        session.getTransaction().commit();
        session.close();
        return d;
    }



    public GeneralInfo getGeneralInfo(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        GeneralInfo gı= session.get(GeneralInfo.class, i);
        session.getTransaction().commit();
        session.close();

        return gı;
    }

    

    public Medication getMedication(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Medication m= session.get(Medication.class,i);
        session.getTransaction().commit();
        session.close();
        return  m;
    }
    public Medication getMedication(String name) throws SQLException{
        session= factory.getCurrentSession();
        session.beginTransaction();
        Medication m= session.get(Medication.class,getIdByName(name, "Medication"));
        session.getTransaction().commit();
        session.close();
        return  m;
    }
    

    public Prescription getPrescription(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Prescription p= session.get(Prescription.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }
    

    public Admin getAdmin(int i){

        session= factory.getCurrentSession();
        session.beginTransaction();
        Admin p= session.get(Admin.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public Department getDepartment(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Department p= session.get(Department.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public Doctor getDoctor(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Doctor p= session.get(Doctor.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }
    public Doctor getDoctor(String name, String surname) throws SQLException{
        session= factory.getCurrentSession();
        session.beginTransaction();
        Doctor p= session.get(Doctor.class, getIdByNameSurname(name, surname, "Doctor"));

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public Hospital getHospital(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Hospital p= session.get(Hospital.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }
    public Hospital getHospital(String name) throws SQLException{
        session= factory.getCurrentSession();
        session.beginTransaction();
        Hospital p= session.get(Hospital.class, getIdByName(name, "Hospital"));

        session.getTransaction().commit();
        session.close();
        return p;
    }

    



    public Patient getPatient(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Patient p= session.get(Patient.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }
    public Patient getPatient(String name, String surname) throws SQLException{
        session= factory.getCurrentSession();
        session.beginTransaction();
        Patient p= session.get(Patient.class, getIdByNameSurname(name,surname, "Patient"));

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public Person getPerson(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Person p= session.get(Person.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public Task getTask(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Task p= session.get(Task.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public Appointment getAppointment(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Appointment p= session.get(Appointment.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public DailySchedule getDailySchedule(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        DailySchedule p= session.get(DailySchedule.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }

    public Schedule getSchedule(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Schedule p= session.get(Schedule.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }





    public static void main(String[] args) throws SQLException {
        Database database= new Database();

        System.out.println(database.getAvailableCounty("Hospital","Ankara"));
    }
}
