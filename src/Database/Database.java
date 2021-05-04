package Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import Person.*;
import Schedule.*;
import GeneralInfo.*;

public class Database {

    Configuration configuration;
    SessionFactory factory;
    Session session;
    Connection connection;

    public Database(){
        try {
            configuration = new Configuration();
            configuration.configure("Database/hibernate.cfg.xml");

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
            
            String sId= rs.getString("id");
            statement.close();
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

    public int getPersonIdByMail(String mail) throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT id FROM Person WHERE email= '"+mail+"';";

        ResultSet rs= statement.executeQuery(sql);

        while(rs.next()){
            
            int id= rs.getInt("id");
            statement.close();
            return id;
        }
        
        statement.close();
        return 0;
    }

    public Doctor getDoctorByMail(String mail) throws SQLException{
        int id= getPersonIdByMail(mail);
        Doctor doctor= getDoctor(id);
        return doctor;
    }

    public Admin getAdminByMail(String mail) throws SQLException{
        int id= getPersonIdByMail(mail);

        Admin admin= getAdmin(id);
        return admin;
    }
    public Patient getPatientByMail(String mail) throws SQLException{
        int id= getPersonIdByMail(mail);

        Patient patient= getPatient(id);
        return patient;
    }

    public ArrayList<String> getAvailableCity(String objectName) throws SQLException{
        Statement statement= connection.createStatement();
        
        String sql= "SELECT Address.city FROM "+objectName+" JOIN Address ON "+objectName+".Address_id = Address.id;" ;

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

    public ArrayList<String> getAllDepartments() throws SQLException{
        Statement statement= connection.createStatement();
        String sql= "SELECT name FROM Department";

        ResultSet rs= statement.executeQuery(sql);
        ArrayList<String> departments= new ArrayList<>();

        while(rs.next()){
                departments.add(rs.getString("name"));
        }

        statement.close();
        return (ArrayList<String>)departments.stream().distinct().collect(Collectors.toList());

    }

    public ArrayList<Hospital> getAllHospitalsIn(String city, String county) throws SQLException {
        Statement statement= connection.createStatement();
        String sql= "SELECT Hospital.id FROM Hospital JOIN Address ON Hospital.Address_id = Address.id WHERE city= '"+city+"' AND county='"+county+"';";
        System.err.println(sql);

        ResultSet rs= statement.executeQuery(sql);
        ArrayList<Hospital> arr= new ArrayList<>();

        while(rs.next()){
            arr.add(getHospital(rs.getInt("id")));
        }

        statement.close();
        
        
        return arr;
    }

    public void deleteDoctor(String name) throws SQLException {

            String sql = "DELETE FROM Person WHERE name = ? LIMIT 1 ";
            String isbn = name;

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, isbn);

            int rows = statement.executeUpdate();

    }

    public void deleteTask(String name) throws SQLException {

        String sql = "DELETE FROM Task WHERE name = ? LIMIT 1";
        String isbn = name;

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, isbn);

        int rows = statement.executeUpdate();

    }

    

    public boolean add(Object o){
        session= factory.getCurrentSession();
        session.beginTransaction();

        session.save(o);
        
        
        session.getTransaction().commit();
        session.close();

        return true;
    }

    public boolean update(Object o){
        
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

    public Department getDepartment(String s,Hospital h) throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT id FROM Department WHERE name= '"+s+"' AND Hospital_id= '"+h.getId()+"';";

        System.out.println(sql);

        ResultSet rs= statement.executeQuery(sql);
        int id= 0;

        while(rs.next()){
            id= rs.getInt("id");
            break;
        }
        statement.close();

        if(id==0){
            return null;
        }

        return getDepartment(id);
    }

    public Doctor getDoctor(int i){
        session= factory.getCurrentSession();
        session.beginTransaction();
        Doctor p= session.get(Doctor.class, i);

        session.getTransaction().commit();
        session.close();
        return p;
    }
    public Doctor getDoctor(String name) throws SQLException{
        session= factory.getCurrentSession();
        session.beginTransaction();
        Doctor p= session.get(Doctor.class, getIdByName(name, "Person")) ;

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
    public Patient getPatient(String name) throws SQLException{
        session= factory.getCurrentSession();
        session.beginTransaction();

        Patient p= session.get(Patient.class, getIdByName(name, "Person"));

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
    /**
    * Kapasite almak için hospital.getIcu
    * Doktor sayısı için hospital.getHospitalDoctors.size()
    * Departman sayısı için hospital.getDepartments.size()
    */
    public ArrayList<Hospital>getAllHospital() throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT id FROM Hospital;";
        

        ResultSet rs= statement.executeQuery(sql);
        ArrayList<Hospital> arr= new ArrayList<>();

        while(rs.next()){
            arr.add(getHospital(rs.getInt("id")));
        }
        statement.close();

        return arr;

    }
    /**
    * Hasta sayısı için disease.getPatients.size()
    * 
    * 
    */
    public ArrayList<Disease>getAllDisease( )throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT id FROM Disease;";
        

        ResultSet rs= statement.executeQuery(sql);
        ArrayList<Disease> arr= new ArrayList<>();

        while(rs.next()){
            arr.add(getDisease(rs.getInt("id")));
        }
        statement.close();

        return arr;
    }
    /**
    * kullanan hasta sayısı için medication.getPrescriptions.size()
    * 
    * 
    */
    public ArrayList<Medication> getAllMedication() throws SQLException{
        Statement statement= connection.createStatement();

        String sql= "SELECT id FROM Medication;";
        

        ResultSet rs= statement.executeQuery(sql);
        ArrayList<Medication> arr= new ArrayList<>();

        while(rs.next()){
            arr.add(getMedication(rs.getInt("id")));
        }
        statement.close();

        return arr;
    }

    public void deletePatientFromDoctor(Doctor d, Patient p) throws SQLException{
        Statement statement= connection.createStatement();
        int d_id= d.getId();
        int p_id= p.getID();
        String sql= "DELETE FROM DoctorPatientJoin WHERE Doctor_id="+d_id+" AND Patient_id="+p_id+";";

        System.out.println(sql);

        statement.executeUpdate(sql);
        statement.close();

    }

    

    public static void main(String[] args) throws SQLException {
        Database database= new Database();

        //ANKARA
        Hospital bayındır= new Hospital("Bayındır", 100, "0312345565656", "bayındır@bayındır.com.tr");
        Address bayındırAdress= new Address("Ankara", "Sögütözü", "3056 sok");

        Hospital hacettepe= new Hospital("Hacettepe",200,"0312 123 3232","hacettepe@hacettepe.com.tr");
        Address hacettepeAddress= new Address("Ankara", "Altındağ", "1234.sok");

        
        
       
        
        
        
        

        

            
    } 


}
