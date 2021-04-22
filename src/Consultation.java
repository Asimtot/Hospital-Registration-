import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Consultation")
public class Consultation {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "DiseaseConsultationJoin",
            joinColumns = @JoinColumn(name= "Consultation_id"),
            inverseJoinColumns = @JoinColumn(name="Disease_id"))
    private List<Disease> diagnosis;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Prescription_id",
                referencedColumnName = "id")
    private Prescription prescription;

    //DATABASE için gerekli
    @ManyToOne
    @JoinColumn(name = "id")
    private GeneralInfo generalInfo;

}
