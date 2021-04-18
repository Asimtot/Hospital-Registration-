import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Department")
public class Department {

    @DatabaseField(generatedId = true, columnName ="department_id" )
    private int department_id;

    @DatabaseField
    private String name;
    @DatabaseField
    private String equipment;

    public String getName() {
        return name;
    }

    public String getEquipment() {
        return equipment;
    }



    public Department(String name, String eguipment) {
        this.name = name;
        this.equipment = eguipment;
    }
    Department(){}

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", eguipment='" + equipment + '\'' +
                '}';
    }
}
