import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import GeneralInfo.Body;
import GeneralInfo.BodyPart;
import GeneralInfo.Consultation;
import GeneralInfo.Disease;
import GeneralInfo.FamilyTree;
import GeneralInfo.GeneralInfo;
import GeneralInfo.Medication;
import GeneralInfo.Prescription;

import Person.*;
import Schedule.*;


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
            configuration.addAnnotatedClass(GeneralInfo.class);
            configuration.addAnnotatedClass(FamilyTree.class);
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

        //Prescription p=session.get(Prescription.class,1);

        //System.out.println(p.getMedications().size());

       

        





        session.getTransaction().commit();
        session.close();

        return true;
    }
    public static void main(String[] args) {
        Database database= new Database();
        database.add();

    }
}
