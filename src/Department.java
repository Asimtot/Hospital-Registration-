import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

@DatabaseTable(tableName = "Department")
public class Department {

    @DatabaseField(generatedId = true, columnName ="department_id" )
    private int department_id;

    @DatabaseField
    private String name;
    @DatabaseField
    private String equipment;

    @ForeignCollectionField(eager = false)
    private Collection<Doctor> doctors;

    public String getName() {
        return name;
    }

    public String getEquipment() {
        return equipment;
    }

    public Collection<Doctor> getDoctors() {
        return doctors;
    }

    public Department(String name, String eguipment) {
        this.name = name;
        this.equipment = eguipment;
        doctors= new ArrayList<>();
    }
    Department(){}

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", eguipment='" + equipment + '\'' +
                '}';
    }
    void addDoctor(Doctor d){
        doctors.add(d);
    }
}
