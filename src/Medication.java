import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Medication")
public class Medication {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "MedicationDiseaseJoin",
               joinColumns = @JoinColumn(name= "Medication_id"),
               inverseJoinColumns = @JoinColumn(name="Disease_id"))
    private List<Disease> dClashes;

    //DATABASE için gerekli
    @ManyToMany(mappedBy = "medications")
    private List<Prescription> prescriptions;


}
