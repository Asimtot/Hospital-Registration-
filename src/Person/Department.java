package Person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    String departmentName;
    @OneToMany(mappedBy = "department")
    List<Doctor> doctors;

    public Department(String departmentName) {
        doctors = new ArrayList<Doctor>();
        this.departmentName = departmentName;
    }

    //getter
    public ArrayList<Doctor> getDepartmentDoctors() {
        return (ArrayList<Doctor>) doctors;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param d
     */
    public void addDocToDepartment( Doctor d){
        doctors.add(d);
    }
}
