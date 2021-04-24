import java.time.LocalDateTime;
import java.util.ArrayList;


public class Appointment implements Comparable{
    // properties
    String name;
    ArrayList<Person> people;
    LocalDateTime startingTime;
    LocalDateTime endingTime;
    Hospital place;
    Department department;
    int timeInterval;

    // constructor
    // simple
    public Appointment(){
        people = new ArrayList<Person>();
    }
    // complete
    public Appointment(String name, ArrayList<Person> people, Hospital place, Department department, int timeInterval,
                       int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this.name = name;
        this.people = people;
        this.place = place;
        this.department = department;
        this.timeInterval = timeInterval;

        startingTime = LocalDateTime.of(year,month,dayOfMonth,hourOfDay,minute);
        endingTime = startingTime.plusMinutes(timeInterval);
    }

    @Override
    public int compareTo(Object o) {
        if ( !(o instanceof Appointment))
            return 0;
        return this.getStartingTime().compareTo(((Appointment) o).getStartingTime());
    }

    // getters
    public String getName() {
        return name;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public Department getDepartment() {
        return department;
    }

    public Hospital getPlace() {
        return place;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    // setters


    public void setName(String name) {
        this.name = name;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public void addPerson(Person p){
        people.add(p);
    }

    public void setPlace(Hospital place) {
        this.place = place;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public void setStartingTime(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        startingTime = LocalDateTime.of(year,month,dayOfMonth,hourOfDay,minute);
    }

    public boolean setEndingTime(LocalDateTime endingTime) {
        if(endingTime.isAfter(startingTime)) {
            this.endingTime = endingTime;
            return true;
        }
        else
            return false;
    }

    public boolean setEndingTime(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        if(endingTime.isAfter(startingTime)) {
            endingTime = LocalDateTime.of(year,month,dayOfMonth,hourOfDay,minute);
            return true;
        }
        else
            return false;

    }

    /**
     * Sets the ending time according to the time interval of the appointment and the starting time
     * @param timeInterval in minutes
     */
    public void setEndingTime(int timeInterval) {
        this.timeInterval = timeInterval;
        if(startingTime != null){
            endingTime = startingTime.plusMinutes(timeInterval);
        }
    }
}

