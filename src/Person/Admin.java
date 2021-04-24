package Person;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "Admin")
public class Admin extends Person{

    // properties
    @OneToOne
    @JoinColumn(name = "Hospital_id")
    Hospital hospital;

    public Admin(){}

    public Admin(Hospital hospital){
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
