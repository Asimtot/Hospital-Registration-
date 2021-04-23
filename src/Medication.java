import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "MedicationDiseaseJoin",
               joinColumns = @JoinColumn(name= "Medication_id"),
               inverseJoinColumns = @JoinColumn(name="Disease_id"))
    private List<Disease> dClashes= new ArrayList<>();

        //DATABASE için gerekli parametreler
    @ManyToMany(mappedBy = "medications")
    private List<Prescription> prescriptions;

        //CONSTRUCTORS
    Medication(){}

        //METHODS
    void addClasshingDisease(Disease d){
        dClashes.add(d);
    }

            //GETTERS
    public int getId() {
        return id;
    }
    
    public List<Disease> getdClashes() {
        return dClashes;
    }
}
