package GeneralInfo;

import javax.persistence.*;

import Person.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FamilyTree")
public class FamilyTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "familyTree")
    List<Person> allowedRelatives= new ArrayList<>();
    
    //DATABASE için gerekli
    @OneToMany(mappedBy = "familyTree")
    List<GeneralInfo> generalInfoList;

        //CONSTRUCTORS

    FamilyTree(){}

        //METHODS

            //GETTERS

    public int getId() {
        return id;
    }
}
