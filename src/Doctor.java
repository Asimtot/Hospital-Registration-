//TEST AMAÇLI
public class Doctor {
    private int ID;
    private String name,surname;

    public Doctor(int ID, String name, String surname) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
}
