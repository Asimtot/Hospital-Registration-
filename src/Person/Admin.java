package Person;

import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "Admin")
public class Admin extends Person{

    // properties

    @OneToOne
    @JoinColumn(name = "Hospital_id")
    Hospital hospital;

    public Admin(String name, String email, String password, Hospital hospital){
        super(name,email,password);
        this.hospital= hospital;
    }
    public Admin(String name, String email, String password){
        super(name,email,password);
    }

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
