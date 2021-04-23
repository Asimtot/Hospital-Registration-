import java.util.ArrayList;

/** Family Tree
 *  @author Efe Can Tepe
 *  @version 04.18.2021
 */
public class FamilyTree {
    
    private PatientNode head;

    public FamilyTree(Patient patient){

        head = new PatientNode(patient);    
    }

    public class PatientNode{

       public Patient patient;
       public PatientNode father;
       public PatientNode mother;
       public PatientNode partner;
       public ArrayList<PatientNode> siblings;
       public ArrayList<PatientNode> children;

        public PatientNode(Patient patient){
            this.patient = patient;
        }


       public void traverse(){
           
       }



    }


}
