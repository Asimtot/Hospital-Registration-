

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

            System.out.println("Bağlandı");
        }
        catch (Exception e){
            System.out.println("Bağlanamadı");
            System.out.println(e.getMessage());
        }

    }

    boolean add(){
        factory= configuration.buildSessionFactory();
        session= factory.getCurrentSession();
        session.beginTransaction();

        Medication m= session.get(Medication.class,8);
        Medication n= session.get(Medication.class,9);

        Department d= session.get(Department.class,4);
        Department b= session.get(Department.class,5);

        Hospital p= session.get(Hospital.class, 2);

       System.out.println(p.getDepartments().size());
        

        session.save(new Hospital("Hastane",122));
        


        session.getTransaction().commit();
        session.close();

        return true;
    }
    public static void main(String[] args) {
        Database database= new Database();
        database.add();
    }
}
