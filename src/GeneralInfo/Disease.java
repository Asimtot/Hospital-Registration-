package GeneralInfo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "BodyPart_id")
    private BodyPart relatedBodyPart;

    //DATABASE için gerekliler
    @ManyToMany(mappedBy = "dClashes")
    private List<Medication> clashingMedication;

    @ManyToMany(mappedBy = "diagnosis")
    private List<Consultation> consultations;

        //CONSTRUCTORS
    Disease(){};

        //METHODS
    void setRelatedBodyPart(BodyPart bp){
        relatedBodyPart= bp;
    }

            //GETTERS
    public int getId() {
        return id;
    }

    public BodyPart getRelatedBodyPart() {
        return relatedBodyPart;
    }


}
