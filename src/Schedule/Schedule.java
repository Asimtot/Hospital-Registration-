package Schedule;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    // properties
    List<DailySchedule> days;
    int startingHour;
    int startingMinute;
    int endingHour;
    int endingMinute;

    // constructor
    // simple
    public Schedule(){
        days = new ArrayList<>();
        startingHour = 8;
        startingMinute = 0;
        endingHour = 17;
        endingMinute = 0;
    }

    // complete
    public Schedule(int startingHour, int startingMinute, int endingHour, int endingMinute) {
        days = new ArrayList<>();
        this.startingHour = startingHour;
        this.startingMinute = startingMinute;
        this.endingHour = endingHour;
        this.endingMinute = endingMinute;
    }

    // methods

    public boolean addAppointment(Appointment app){
        DailySchedule day = findDay(app.getStartingTime());
        if (day != null){
            return day.addAppointment(app);
        }
        // if the day is not in the schedule, create that day and add the appointment
        else{
            LocalDateTime date = app.getStartingTime();
            DailySchedule newDay = new DailySchedule(date.getYear(),date.getMonthValue(), date.getDayOfMonth(),
                    startingHour, startingMinute, endingHour, endingMinute);
            days.add(newDay);
            return newDay.addAppointment(app);
        }
    }

    public boolean cancelAppointment(Appointment app){
        DailySchedule day = findDay(app.getStartingTime());
        if (day == null){
            return false;
        }
        else{
            return day.cancelAppointment(app);
        }
    }

    public boolean postponeAppointment(Appointment app, int newYear, int newMonth, int newDayOfMonth, int newHour, int newMinute){
        DailySchedule day = findDay(app.getStartingTime());
        if (day == null){
            return false;
        }
        else{
            // create a new appointment with a new date
            Appointment newApp = new Appointment(app.getName(), app.getDoctor(), app.getPatient(), app.getPlace(), app.getDepartment(), app.getTimeInterval(),
                    newYear, newMonth, newDayOfMonth, newHour, newMinute);

            // if the appointment was successfully added, then cancel the odd appointment
            if(addAppointment(newApp)){
                day.cancelAppointment(app);
                return true;
            }
            else return false;
        }
    }

    public boolean postponeAppointment(Appointment app, LocalDateTime newDate){
        DailySchedule day = findDay(app.getStartingTime());
        if (day == null){
            return false;
        }
        else{
            // create a new appointment with a new date
            Appointment newApp = new Appointment(app.getName(), app.getDoctor(), app.getPatient(), app.getPlace(), app.getDepartment(), app.getTimeInterval(),
                   newDate);

            // if the appointment was successfully added, then cancel the odd appointment
            if(addAppointment(newApp)){
                day.cancelAppointment(app);
                return true;
            }
            else return false;
        }
    }

    public ArrayList<LocalDateTime> getAvailableIntervals(LocalDateTime date){
        DailySchedule day = findDay(date);
        if (day == null){
            day = new DailySchedule(date.getYear(),date.getMonthValue(), date.getDayOfMonth(),
                    startingHour, startingMinute, endingHour, endingMinute);
            ((ArrayList<DailySchedule>)days).add(day);
        }
        return day.getAvailableIntervals();
    }

    public ArrayList<Appointment> getDateAppointments(LocalDateTime date){
        DailySchedule day = findDay(date);
        if (day != null){
            return day.getAppointments();
        }
        // if the day is not in the schedule, create that day
        else{
            DailySchedule newDay = new DailySchedule(date.getYear(),date.getMonthValue(), date.getDayOfMonth(),
                    startingHour, startingMinute, endingHour, endingMinute);
            days.add(newDay);
            return newDay.getAppointments();
        }
    }

    private DailySchedule findDay(LocalDateTime appDate){
        LocalDateTime date;
        ((ArrayList<DailySchedule>)days).trimToSize();
        for (int i = days.size() - 1; i >= 0; i--) {
            date = days.get(i).getDate();
            if (date.getDayOfMonth() == appDate.getDayOfMonth() && date.getMonthValue() == appDate.getMonthValue()
                    && date.getYear() == appDate.getYear()){
                return days.get(i);
            }
        }
        return null;
    }

    // getters

    public ArrayList<DailySchedule> getDays() {
        return (ArrayList<DailySchedule>) days;
    }

    public int getStartingHour() {
        return startingHour;
    }

    public int getStartingMinute() {
        return startingMinute;
    }

    public int getEndingHour() {
        return endingHour;
    }

    public int getEndingMinute() {
        return endingMinute;
    }

    // setters
    public void setStartingHour(int startingHour) {
        this.startingHour = startingHour;
    }

    public void setStartingMinute(int startingMinute) {
        this.startingMinute = startingMinute;
    }

    public void setEndingHour(int endingHour) {
        this.endingHour = endingHour;
    }

    public void setEndingMinute(int endingMinute) {
        this.endingMinute = endingMinute;
    }
}
