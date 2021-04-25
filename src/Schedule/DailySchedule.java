package Schedule;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Simulates one day of the schedule
 * IMPORTANT: if the simple constructor is used, then the setting order must be:
 *            setDate - setStartingTime - setEndingTime
 */
@Entity
@Table(name = "DailySchedule")
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    // properties
    //TODO 
    //MAP 
    String sDate; //date 21-2-1212
    String sStartingTime; //datetime 12-12-2222:13:35
    String sEndingTime;//datetime


    LocalDateTime date;
    LocalDateTime startingTime;
    LocalDateTime endingTime;

    @OneToMany(mappedBy = "dailySchedule")
    List<Appointment> appointments;

    // constructors
    // simple
    public DailySchedule(){
        appointments = new ArrayList<>();
    }

    //DATABASE için gerekli
    @ManyToOne
    @JoinColumn(name = "Schedule_id")
    Schedule schedule;

    // complete
    public DailySchedule(int year, int month, int dayOfMonth, int startingHour, int startingMinute, int endingHour, int endingMinute) {
        date = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
        startingTime = LocalDateTime.of(year, month, dayOfMonth, startingHour, startingMinute);
        endingTime = LocalDateTime.of(year, month, dayOfMonth, endingHour, endingMinute);
        appointments = new ArrayList<>();
    }

    public boolean addAppointment(Appointment app){
        boolean clashes = false;
        // check if the appointment is during the working hours
        if (app.getStartingTime().isAfter(startingTime) && app.getEndingTime().isBefore(endingTime)){
            // check if the given appointment clashes with any of the previous appointments
            for (Appointment a : appointments) {
                if (app.getStartingTime().isAfter(a.getStartingTime()) && app.getStartingTime().isBefore(a.getEndingTime())
                        || (app.getEndingTime().isAfter(a.getStartingTime()) && app.getEndingTime().isBefore(a.getStartingTime()))){
                    clashes = true;
                    break;
                }
            }
            if (!clashes)
                appointments.add(app);
            return clashes;
        }
        else
            return false;
    }

    public boolean cancelAppointment(Appointment app){
        int index = appointments.indexOf(app);
        if (index < 0)
            return false;
        else{
            appointments.remove(index);
            return true;
        }
    }

    public ArrayList<LocalDateTime> getAvailableIntervals(){
        ArrayList<LocalDateTime> intervals = new ArrayList<>();
        if (appointments.size() > 0) {
            Collections.sort(appointments);
            // starting time
            if (startingTime.isBefore(appointments.get(0).getStartingTime())) {
                intervals.add(startingTime);
                intervals.add(appointments.get(0).getStartingTime());
            }
            // other intervals
            for (int i = 0; i < appointments.size() - 1; i++) {
                if (appointments.get(i).getEndingTime().isBefore(appointments.get(i + 1).getStartingTime())) {
                    intervals.add(appointments.get(i).getEndingTime());
                    intervals.add(appointments.get(i + 1).getStartingTime());
                }
            }
            // ending time
            if (appointments.get(appointments.size() - 1).getEndingTime().isBefore(endingTime)) {
                intervals.add(appointments.get(appointments.size() - 1).getEndingTime());
                intervals.add(endingTime);
            }
        }
        else{
            intervals.add(startingTime);
            intervals.add(endingTime);
        }
        return intervals;
    }

    // setters


    // IMPORTANT: the setting order: setDate - setStartingTime - setEndingTime


    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // no setAppointments method - use addAppointment instead to prevent clashes

    public boolean setStartingTime(LocalDateTime startingTime) {
        if(startingTime.isAfter(date) && startingTime.isBefore(date.plusDays(1))){
            this.startingTime = startingTime;
            return true;
        }
        return false;

    }

    public boolean setStartingTime(int year, int month, int dayOfMonth, int hour, int minute) {
        if(startingTime.isAfter(date) && startingTime.isBefore(date.plusDays(1))){
            this.startingTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            return true;
        }
        return false;
    }

    public boolean setEndingTime(LocalDateTime endingTime) {
        if(endingTime.isAfter(startingTime) && endingTime.isBefore(date.plusDays(1))){
            this.endingTime = endingTime;
            return true;
        }
        return false;
    }

    public boolean setEndingTime(int year, int month, int dayOfMonth, int hour, int minute) {
        if(endingTime.isAfter(startingTime) && endingTime.isBefore(date.plusDays(1))){
            this.endingTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            return true;
        }
        return false;
    }

    // getters
    public ArrayList<Appointment> getAppointments() {
        return (ArrayList<Appointment>) appointments;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }
}
