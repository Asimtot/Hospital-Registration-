package GeneralInfo;

import javax.persistence.*;

import Person.Department;
import Person.Patient;

import java.util.List;

@Entity
@Table(name = "Disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "BodyPart_id")
    private BodyPart relatedBodyPart;

    @Column(name = "info")
    private String info;

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "Department_id")
    private Department relatedField;


        //DATABASE için gerekliler
    @ManyToMany(mappedBy = "dClashes")
    private List<Medication> clashingMedication;

    @ManyToMany(mappedBy = "diagnosis")
    private List<Consultation> consultations;

    @ManyToMany(mappedBy = "activeDiseases")
    private List<Patient> patients;

    

        //CONSTRUCTORS
    Disease(){};


    public Disease(String info, String name) {
        this.info = info;
        this.name = name;
    }


    

        //METHODS
    public void setRelatedBodyPart(BodyPart bp){
        relatedBodyPart= bp;
    }

    public void setInfo(String s){
        info= s;
    }

    public void setRelatedField(Department relatedField) {
        this.relatedField = relatedField;
    }

            //GETTERS
    public int getId() {
        return id;
    }

    public BodyPart getRelatedBodyPart() {
        return relatedBodyPart;
    }

    public List<Medication> getClashingMedication() {
        return clashingMedication;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public Department getRelatedField() {
        return relatedField;
    }
}
