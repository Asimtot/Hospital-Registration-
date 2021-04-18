import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//TEST AMAÇLI
@DatabaseTable(tableName = "Doctor")
public class Doctor {

    @DatabaseField (id = true)
    private int ID;
    @DatabaseField
    private String name;
    @DatabaseField
    private String surname;

    public void setDepartment(Department department) {
        this.department = department;
    }

    @DatabaseField(foreign = true, foreignAutoCreate = true,foreignAutoRefresh = true)
    private Department department;

    public Doctor(int ID, String name, String surname) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
    }
    Doctor(){}

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department=" + department +
                '}';
    }
}
