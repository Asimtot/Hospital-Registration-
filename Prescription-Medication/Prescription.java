import java.util.ArrayList;

public class Prescription {

    private ArrayList<Medication> medications;
    private String frequency; // TODO: WTF is that? 

    public Prescription() {

    }

    public String getFrequency(){
        return frequency;
    }

    public ArrayList<Medication> getMedications(){
        return medications;
    }

    public void addMedication(Medication med){
        medications.add(med);
    }

    public void setFrequency(){
        
    }

}
