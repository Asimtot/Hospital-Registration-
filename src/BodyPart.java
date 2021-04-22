import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BodyPart")
public class BodyPart {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    //DATABASE için gerekli
    @ManyToMany(mappedBy = "parts")
    private List<Body> bodys;

    @OneToMany(mappedBy = "relatedBodyPart")
    List<Disease> diseases;
}
