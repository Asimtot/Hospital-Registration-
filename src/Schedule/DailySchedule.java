package Schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Daily Schedule Class
 * Simulates one day of the schedule
 * IMPORTANT: if the simple constructor is used, then the setting order must be:
 *            setDate - setStartingTime - setEndingTime
 * @author Kardelen Ceren
 */
@Entity
@Table(name = "DailySchedule")
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "date")
    String dateStr;
    @Column(name = "start")
    String startingTimeStr;
    @Column(name = "end")
    String endingTimeStr;


    @Transient
    private LocalDateTime date;
    @Transient
    LocalDateTime startingTime;
    @Transient
    LocalDateTime endingTime;

    //necessary for DATABASE

    @ManyToOne
    @JoinColumn(name = "Schedule_id")
    Schedule schedule;

    @OneToMany(mappedBy = "dailySchedule",cascade = CascadeType.ALL)
    List<Appointment> appointments;

    // constructors
    // simple
    public DailySchedule(){
        appointments = new ArrayList<>();
    }

    

    // complete
    public DailySchedule(int year, int month, int dayOfMonth, int startingHour, int startingMinute, int endingHour, int endingMinute) {
        date = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
        
        startingTime = LocalDateTime.of(year, month, dayOfMonth, startingHour, startingMinute);
        endingTime = LocalDateTime.of(year, month, dayOfMonth, endingHour, endingMinute);
        appointments = new ArrayList<>();

        startingTimeStr= Converter.toString(startingTime);
        endingTimeStr= Converter.toString(endingTime);
        dateStr= Converter.toString(date);
    }

    /**
     * adding appointment to DailySchedule
     * @param app Appointment object
     * @return true if it is added successfully
     *         false otherwise
     */
    public boolean addAppointment(Appointment app){

        boolean clashes = false;
        // check if the appointment is during the working hours
        if (app.getStartingTime().isAfter(startingTime) && app.getEndingTime().isBefore(endingTime)){
            // check if the given appointment clashes with any of the previous appointments
            System.out.println("1.TAMAM");
            for (Appointment a : appointments) {
                if (app.getStartingTime().isAfter(a.getStartingTime()) && app.getStartingTime().isBefore(a.getEndingTime())
                        || (app.getEndingTime().isAfter(a.getStartingTime()) && app.getEndingTime().isBefore(a.getStartingTime()))){
                    clashes = true;System.out.println("CLASHES");
                    break;
                }
            }
            if (!clashes){
                System.out.println("BAŞARILI");
                appointments.add(app);
                app.setDailySchedule(this);
            }
            return clashes;
        }
        else
            return false;
    }

    /**
     * cancel the appointment from appointmnents list
     * @param app
     * @param app Appointment object
     * @return true if it is cancelled successfully
     *         false otherwise
     */
    public boolean cancelAppointment(Appointment app){
        int index = appointments.indexOf(app);
        if (index < 0)
            return false;
        else{
            appointments.remove(index);
            return true;
        }
    }

    /**
     * it gives the free LocalDateTime objects
     * @return available time as List object
     */
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
    public void convertBack(){
        date= Converter.toLocalDateTime(dateStr);
        startingTime= Converter.toLocalDateTime(startingTimeStr);
        endingTime= Converter.toLocalDateTime(endingTimeStr);
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

    public void setDate(){
        date = Converter.toLocalDateTime(dateStr);
    }
    public void setStartingTime(){
        startingTime = Converter.toLocalDateTime(startingTimeStr);
    }
    public void setEndingTime(){
        endingTime = Converter.toLocalDateTime(endingTimeStr);
    }

    public void setDateStr(){
        dateStr = Converter.toString(date);
    }
    public void setStartingTimeStr(){
        startingTimeStr = Converter.toString(startingTime);
    }
    public void setEndingTimeStr(){
        endingTimeStr = Converter.toString(endingTime);
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setStartingTimeStr(String startingTimeStr) {
        this.startingTimeStr = startingTimeStr;
    }

    public void setEndingTimeStr(String endingTimeStr) {
        this.endingTimeStr = endingTimeStr;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    // getters
    public List<Appointment> getAppointments() {
        return appointments;
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
