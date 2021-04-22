import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Prescription")
public class Prescription {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;


    @ManyToMany
    @JoinTable(name = "MedicationPrescriptionJoin",
            joinColumns = @JoinColumn(name= "Prescription_id"),
            inverseJoinColumns = @JoinColumn(name="Medication_id"))
    private List<Medication> medications;

    //DATABASE için gerekli
    @OneToOne(mappedBy = "prescription")
    private Consultation consultation;
}
