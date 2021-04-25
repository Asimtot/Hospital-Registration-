package GeneralInfo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BodyPart")
public class BodyPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //DATABASE için gerekliler
    @ManyToMany(mappedBy = "parts")
    private List<Body> bodys;

    @OneToMany(mappedBy = "relatedBodyPart")
    private List<Disease> diseases;

    @OneToMany(mappedBy = "bodyPart")
    private List<Consultation> consultations;

        //CONSTRUCTORS
    public BodyPart(){}

        //METHODS

            //GETTERS

    public int getId() {
        return id;
    }
}
