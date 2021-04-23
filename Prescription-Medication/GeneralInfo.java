import java.util.ArrayList;
public class GeneralInfo{

    private FamilyTree familyTree;
    private ArrayList<Consultation> consultations;
    private Body body;

    public GeneralInfo(){

        familyTree = new FamilyTree(); // TO_DO How to pass the patient 
        consultations = new ArrayList<Consultation>();
        body = new Body();
    }

    public FamilyTree getFamilyTree (){
        return familyTree;
    }

    public ArrayList<Consultation> getConsultations(){
        return consultations;
    }

    public Body getBody(){
        return body;
    }
     
    
}
