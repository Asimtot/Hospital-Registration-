import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Disease")
public class Disease {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "BodyPart_id")
    private BodyPart relatedBodyPart;

    //DATABASE için gerekli
    @ManyToMany(mappedBy = "dClashes")
    private List<Medication> clashingMedication;

    //DATABASE için gerekli
    @ManyToMany(mappedBy = "diagnosis")
    private List<Consultation> consultations;



}
