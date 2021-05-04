package GeneralInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Prescription class
 * @author efe can tepe
 */
@Entity
@Table(name = "Prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "MedicationPrescriptionJoin",
            joinColumns = @JoinColumn(name= "Prescription_id"),
            inverseJoinColumns = @JoinColumn(name="Medication_id"))
    private List<Medication> medications= new ArrayList<>();

    @Column(name = "frequency")
    private String frequency;


    //DATABASE için gerekli
    @OneToOne(mappedBy = "prescription")
    private Consultation consultation;

        //CONSTRUCTORS
    Prescription(){}


        //METHODS
    public void addMedication(Medication e){
        medications.add(e);
    } //adding medicines to prescription

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }// frequency of usage for medicine as string

        //GETTERS
    public int getId() {
        return id;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public String getFrequency() {
        return frequency;
    }


}
