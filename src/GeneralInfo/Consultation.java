package GeneralInfo;

import javax.persistence.*;

import Person.Doctor;
import Schedule.Converter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Consultation")
public class Consultation{

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

    @ManyToOne
    @JoinColumn(name = "Doctor_id")
    private Doctor doctor;

    
    @Column(name = "date")
    private String dateStr; //YYYY-MM-DD hh:mm:ss[.nnn] (SQL dateTime format)

    @Transient
    private LocalDateTime date;

    @Column(name = "notes")
    private String notes;
    @Column(name = "type")
    private String type;



    //DATABASE için gerekli
    @ManyToOne
    @JoinColumn(name = "GeneralInfo_id", referencedColumnName = "id")
    private GeneralInfo generalInfo;



    // CONSTRUCTORS
    public Consultation(){}

    // METHODS

    public void addDiseaseToDiagnosis(Disease d){
        diagnosis.add(d);
    }

    public void setDate(){
        date = Converter.toLocalDateTime(dateStr);
    }
    public void setDateStr(){
        dateStr = Converter.toString(date);
    }

    // setters
    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;

    }
    public void setGeneralInfo(GeneralInfo generalInfo){
        this.generalInfo= generalInfo;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
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

    //****

    
    public String showSendable() { //FIXME (not everything has toString)
        return "Consultation{" +
                "diagnosis=" + diagnosis +
                ", prescription=" + prescription +
                ", bodyPart=" + bodyPart +
                ", date=" + date +
                '}';
    }


}
