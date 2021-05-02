package GeneralInfo;

import Person.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GeneralInfo")
public class GeneralInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "generalInfo")
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

        //METHODS
    public void addConsultation(Consultation e){
        e.setGeneralInfo(this);
        consultations.add(e);
    }

    public Consultation getLastConsultation(){
        ((ArrayList<Consultation>)consultations).trimToSize();
        if (consultations.size() != 0)
            return consultations.get(consultations.size() - 1);
        return null;
    }

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
