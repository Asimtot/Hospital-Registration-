import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Body")
public class Body {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "BodyPartBodyJoin",
            joinColumns = @JoinColumn(name= "Body_id"),
            inverseJoinColumns = @JoinColumn(name="BodyPart_id_id"))
    private List<BodyPart> parts;
}
