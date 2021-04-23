import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Body")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "BodyPartBodyJoin",
            joinColumns = @JoinColumn(name= "Body_id"),
            inverseJoinColumns = @JoinColumn(name="BodyPart_id"))
    private List<BodyPart> parts= new ArrayList<>();

    //DATABASE için gerekliler
    @OneToOne(mappedBy = "body")
    private GeneralInfo generalInfo;

        //CONSTRUCTORS
    Body(){}

        //METHODS
    void addBodyPart(BodyPart bp){
        parts.add(bp);
    }


            //GETTERS

    public int getId() {
        return id;
    }

    public List<BodyPart> getParts() {
        return parts;
    }
}
