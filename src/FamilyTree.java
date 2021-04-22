import javax.persistence.*;

@Entity
@Table(name = "FamilyTree")
public class FamilyTree {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
}
