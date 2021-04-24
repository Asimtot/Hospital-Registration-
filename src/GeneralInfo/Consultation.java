package GeneralInfo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "DiseaseConsultationJoin",
            joinColumns = @JoinColumn(name= "Consultation_id"),
            inverseJoinColumns = @JoinColumn(name="Disease_id"))
    private List<Disease> diagnosis= new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Prescription_id",
            referencedColumnName = "id")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "BodyPart_id")
    private BodyPart bodyPart;

    // DB eksik
    private LocalDateTime date;

    //DATABASE için gerekli
    @ManyToOne
    @JoinColumn(name = "GeneralInfo_id", referencedColumnName = "id")
    private GeneralInfo generalInfo;



        //CONSTRUCTORS
    public Consultation(){}

        //METHODS

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;

    }

    public void addDiseaseToDiagnosis(Disease d){
        diagnosis.add(d);
    }

    void setGeneralInfo(GeneralInfo generalInfo){
        this.generalInfo= generalInfo;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    //GETTERS

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public List<Disease> getDiagnosis() {
        return diagnosis;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                " diagnosis=" + diagnosis +
                ", prescription=" + prescription +
                ", bodyPart=" + bodyPart +
                ", date=" + date +
                '}';
    }
}
