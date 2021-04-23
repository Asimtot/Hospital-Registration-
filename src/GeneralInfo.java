import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GeneralInfo")
public class GeneralInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "generalInfo")
    private List<Consultation> consultations= new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "Body_id")
    private Body body;

    @ManyToOne
    @JoinColumn(name = "FamilyTree_id")
    private FamilyTree familyTree;

        //CONSTRUCTORS
    GeneralInfo(){}

        //METHODS
    void addConsultation(Consultation e){
        e.setGeneralInfo(this);
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setFamilyTree(FamilyTree familyTree) {
        this.familyTree = familyTree;
    }
            //GETTERS

    public Body getBody() {
        return body;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public FamilyTree getFamilyTree() {
        return familyTree;
    }

    public int getId() {
        return id;
    }
}
