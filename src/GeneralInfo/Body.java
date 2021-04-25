package GeneralInfo;

import javax.persistence.*;

import Person.Patient;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Body")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
        @JoinColumn(name = "Patient_id")
        private Patient owner;

    @ManyToMany
    @JoinTable(name = "BodyPartBodyJoin",
            joinColumns = @JoinColumn(name= "Body_id"),
            inverseJoinColumns = @JoinColumn(name="BodyPart_id"))
    private List<BodyPart> parts= new ArrayList<>();

    //DATABASE için gerekliler
    @OneToOne(mappedBy = "body")
    private GeneralInfo generalInfo;

        //CONSTRUCTORS
    public Body(){}


    public Body(int id, Patient owner, List<BodyPart> parts, GeneralInfo generalInfo) {
        this.id = id;
        this.owner = owner;
        this.parts = parts;
        this.generalInfo = generalInfo;
    }

    public Body(Patient owner) {
        this.owner = owner;
    }
    

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
