import java.util.ArrayList;

/**
 * Department Class
 * @author Yusuf Doğan
 * @version 20/04/2021
 */

public class Department {

    //properties
    String departmentName;
    ArrayList<Doctor> departmentDoctors;

    public Department(String departmentName) {
        departmentDoctors = new ArrayList<Doctor>();
        this.departmentName = departmentName;
    }

    //getter
    public ArrayList<Doctor> getDepartmentDoctors() {
        return departmentDoctors;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param d
     */
    public void addDocToDepartment( Doctor d){
        departmentDoctors.add(d);
    }
}
