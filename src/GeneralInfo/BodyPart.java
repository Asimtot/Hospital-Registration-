package GeneralInfo;

import javax.persistence.*;
import java.util.List;

/**
 * BodyPart class to be able to show certain parts of body
 * @author Efe Can Tepe
 */
@Entity
@Table(name = "BodyPart")
public class BodyPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //necessary for DATABASE
    @ManyToMany(mappedBy = "parts")
    private List<Body> bodys;

    @OneToMany(mappedBy = "relatedBodyPart")
    private List<Disease> diseases;

    @OneToMany(mappedBy = "bodyPart")
    private List<Consultation> consultations;

        //CONSTRUCTORS
    public BodyPart(){}

    //GETTERS

    public int getId() {
        return id;
    }
}
