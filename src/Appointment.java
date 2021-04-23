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

    @Override
    public int compareTo(Object o) {
        if ( !(o instanceof Appointment))
            return 0;
        return this.getStartingTime().compareTo(((Appointment) o).getStartingTime());
    }
}

