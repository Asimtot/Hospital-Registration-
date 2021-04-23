import java.util.ArrayList;
public class Consultation implements Sendable{

    private Date date;
    private ArrayList<Disease> diseases;
    private Doctor doctor;
    private String notes;
    private String type;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<BodyPart> bodyParts;


    public Date getDate(){
        return date;
    }

    public ArrayList<Disease> getDiseases(){
        return diseases;
    } 

    public Doctor getDoctor(){
        return doctor;
    }

    public String getNotes(){
        return notes;
    }

    public String getType(){
        return type;
    }

    public ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }

    public ArrayList<BodyPart> bodyParts(){
        return bodyParts;
    }

    @Override
    /**
     *  @author Efe Can Tepe
     *  It will show the sendable consultation
     */
    public String showSendable() {
        
        return null;
    }
}