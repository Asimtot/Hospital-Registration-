import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    //DATABASE için gerekli
    @OneToOne(mappedBy = "prescription")
    private Consultation consultation;

        //CONSTRUCTORS
    Prescription(){}


        //METHODS
    void addMedication(Medication e){
        medications.add(e);
    }

            //GETTERS
    public int getId() {
        return id;
    }

    public List<Medication> getMedications() {
        return medications;
    }
}
