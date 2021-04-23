import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FamilyTree")
public class FamilyTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    //DATABASE için gerekli
    @OneToMany(mappedBy = "familyTree")
    private List<GeneralInfo> generalInfoList;

        //CONSTRUCTORS

    FamilyTree(){}

        //METHODS

            //GETTERS

    public int getId() {
        return id;
    }
}
