import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GeneralInfo")
public class GeneralInfo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "generalInfo")
    private List<Consultation> consultations;


}
