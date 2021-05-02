package Person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import GeneralInfo.Disease;
import Schedule.Appointment;

/**
 * Department Class
 * @author Yusuf Doğan
 * @version 20/04/2021
 */

@Entity
@Table(name = "Department")
public class Department {

    //properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name= "name")
    String departmentName;

    @Column(name= "equipment")
    String equipment;

    @OneToMany(mappedBy = "department")
    List<Doctor> doctors= new ArrayList<>();

    //DATABASE için gerekli
    @OneToMany(mappedBy = "department")
    List<Appointment> appointments;

    @OneToMany(mappedBy = "relatedField",cascade = CascadeType.ALL)
    List<Disease> diseases;

    @OneToMany(mappedBy = "relatedField",cascade = CascadeType.ALL)
    List<Disease> medications;

    @ManyToOne
    @JoinColumn(name = "Hospital_id")
    Hospital hospital;

        //CONSTRUCTORS
    public Department(String departmentName) {
        doctors = new ArrayList<Doctor>();
        this.departmentName = departmentName;
    }

    public Department(){}
    

    //getter
    public List<Doctor> getDepartmentDoctors() {
        return doctors;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param d
     */
    void addDocToDepartment( Doctor d){
        doctors.add(d);
        d.setDepartment(this);
        d.setHospital(hospital);
    }
    //SEETTER
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public int getDoctorNumber() {
        return doctors.size();
    }
}
