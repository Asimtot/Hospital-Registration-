import java.util.*;

/**
 * GeneralInfo class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/ 
public class GeneralInfo {
    
    // Properties
    
    private FamilyTree familyTree;
    private ArrayList<Consultation> consultations;
    private Body body;
    
    // Constructor
    
    public GeneralInfo ( FamilyTree familyTree, ArrayList<Consultation> consultations, Body body )
    {
        this.familyTree = familyTree;
        this.consultations = consultations;
        this.body = body;
    }
    
    // Methods
    
    public FamilyTree getFamilyTree()
    {
        return familyTree;
    }
   
    public ArrayList<Consultation> getConsultations()
    {
        return consultations;
    }
    
    public Body getBody()
    {
        return body;
    }
   
    public void drawBody()
    {
        //??
    }
}