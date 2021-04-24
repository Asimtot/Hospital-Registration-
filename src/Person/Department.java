package Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Department Class
 * @author Yusuf Doğan
 * @version 20/04/2021
 */

public class Department {

    //properties
    String departmentName;
    List<Doctor> departmentDoctors;

    public Department(){
        departmentDoctors = new ArrayList<Doctor>();
    }

    public Department(String departmentName) {
        departmentDoctors = new ArrayList<Doctor>();
        this.departmentName = departmentName;
    }

    // getters
    public ArrayList<Doctor> getDepartmentDoctors() {
        return (ArrayList<Doctor>) departmentDoctors;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    public void addDocToDepartment( Doctor d){
        departmentDoctors.add(d);
    }
}
