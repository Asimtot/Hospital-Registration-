import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    public static void main(String[] args) {
        Database database= new Database();
    }
}
