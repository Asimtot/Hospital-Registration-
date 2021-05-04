package GeneralInfo;

import Person.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GeneralInfo class hold patient information like consultation or their body
 * @author Eylül Badem
 */
@Entity
@Table(name = "GeneralInfo")
public class GeneralInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "generalInfo", cascade = CascadeType.ALL)
    private List<Consultation> consultations= new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "Body_id")
    private Body body;


    @OneToOne(mappedBy = "info")
    Patient patient;

    //CONSTRUCTORS
    public GeneralInfo(Patient patient){
        this.patient = patient;
    }

    public GeneralInfo(){}

    //METHODS

    /**
     * void method that add consultation to his general information
     * @param e Consultation ogject
     */
    public void addConsultation(Consultation e){
        e.setGeneralInfo(this);
        consultations.add(e);
    }

    /**
     * method that gives most recent consultation of patient
     * @return Consultation c
     */
    public Consultation getLastConsultation(){
//        ((ArrayList<Consultation>)consultations).trimToSize();
        if (consultations.size() != 0)
            return consultations.get(consultations.size() - 1);
        return null;
    }

    /**
     * void method that adds related body to his general info
     * @param body is Body object
     */
    public void setBody(Body body) {
        this.body = body;
    }


    //GETTERS

    public Body getBody() {
        return body;
    }

    public List<Consultation> getConsultations() {
        return  consultations;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getId() {
        return id;
    }
}
