import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule {
    // properties
    ArrayList<DailySchedule> days;
    int startingHour;
    int startingMinute;
    int endingHour;
    int endingMinute;

    // constructor

    public Schedule(){
        days = new ArrayList<>();
        startingHour = 8;
        startingMinute = 0;
        endingHour = 17;
        endingMinute = 0;
    }
    // methods

    public boolean addAppointment(Appointment app){
        DailySchedule day = findDay(app.startingTime);
        if (day != null){
            return day.addAppointment(app);
        }
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
            Appointment newApp = new Appointment(app.getName(), app.getPeople(), app.getPlace(), app.getDepartment(), app.getTimeInterval(),
                    newYear, newMonth, newDayOfMonth, newHour, newMinute);
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
            return null;
        }
        else{
            return day.getAvailableIntervals();
        }
    }

    private DailySchedule findDay(LocalDateTime appDate){
        LocalDateTime date;
        days.trimToSize();
        for (int i = days.size() - 1; i >= 0; i--) {
            date = days.get(i).getDate();
            if (date.equals(appDate.withHour(0).withMinute(0))){
                return days.get(i);
            }
        }
        return null;
    }

    // getters

    public ArrayList<DailySchedule> getDays() {
        return days;
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
