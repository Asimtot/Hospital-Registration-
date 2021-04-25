package GeneralInfo;

import javax.persistence.*;

import Person.Department;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medication")
public class Medication {


    @ManyToMany
    @JoinTable(name = "MedicationMedicationJoin",
                joinColumns = @JoinColumn(name= "Medication_id"),
                inverseJoinColumns= @JoinColumn(name= "Medication_id1") )
    List<Medication> mClashes;


    @ManyToOne
    @JoinColumn(name = "Department_id")
    private Department relatedField;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name= "info")
    private String info;

    @ManyToMany
    @JoinTable(name = "MedicationDiseaseJoin",
               joinColumns = @JoinColumn(name= "Medication_id"),
               inverseJoinColumns = @JoinColumn(name="Disease_id"))
    private List<Disease> dClashes= new ArrayList<>();

        //DATABASE için gerekli parametreler
    @ManyToMany(mappedBy = "medications")
    private List<Prescription> prescriptions;

        //CONSTRUCTORS
    Medication(){}

    public Medication(String name, String info){
        this.name= name;
        this.info= info;
    }

        //METHODS
    void addClasshingDisease(Disease d){
        dClashes.add(d);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

            //GETTERS
    public int getId() {
        return id;
    }
    
    public List<Disease> getdClashes() {
        return dClashes;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
             //


    @Override
    public String toString() {
        return "Medication{" +
                "id= " + id +
                "\nname='" + name + '\'' +
                "\ninfo='" + info + '\'' +
                '}';
    }

    
}
