package Person;
import java.util.*;

import GeneralInfo.*;
import Schedule.*;

/**
 * Doctor class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/ 
public class Doctor extends Person {
    
    // Properties
    
    private Hospital hospital;
    private Department department;
    private Schedule schedule;
    private ArrayList<Task> tasks;
    private ArrayList<Patient> patients;
    
    // Constructors
    public Doctor(){}
    
    public Doctor ( String name, String email, String password, Hospital hospital, Department department )
    {
        super( name, email, password );
        this.hospital = hospital;
        this.department = department;
        schedule = new Schedule();
        tasks = new ArrayList<Task>();
        patients = new ArrayList<Patient>();
    }
    
    public Doctor ( String name, String email, String password )
    {
        super( name, email, password );
        schedule = new Schedule();
        tasks = new ArrayList<Task>();
        patients = new ArrayList<Patient>();
    }
    
    // Methods
    
    public Hospital getHospital()
    {
        return hospital;
    }
   
    public Department getDepartment()
    {
        return department;
    }
    
    public Schedule getSchedule()
    {
        return schedule;
    }
    
    public ArrayList<Task> getTasks()
    {
        return tasks;
    }
    
    public ArrayList<Patient> getPatients()
    {
        return patients;
    }
    
    public void setHospital( Hospital hospital )
    {
        this.hospital = hospital;
    }
   
    public void setDepartment( Department department )
    {
        this.department = department;
    }
    
    public void setSchedule( Schedule schedule )
    {
        this.schedule = schedule;
    }
    
    public void setTasks( ArrayList<Task> tasks )
    {
        this.tasks = tasks;
    }
    
    public void setPatients( ArrayList<Patient> patients )
    {
        this.patients = patients;
    }
    
    /**
     * This method adds a wanted task to doctor's tasks list
     * @param newTask any task
     * @return true if the adding process was successful
     */
    public boolean addTask( Task newTask )
    {
        boolean check = false;
        tasks.add(newTask);
        
        if ( tasks.contains( newTask ))
            check = true;
        
        return check;
    }
    
    /**
     * This method removes a wanted task from doctor's tasks list
     * @param oldTask any task in the list
     * @return true if the removing process was successful
     */
    public boolean removeTask( Task oldTask )
    {
        boolean check = true;
        tasks.remove(oldTask);
        
        if ( tasks.contains( oldTask ))
            check = false;
        
        return check;
    }
    
    /**
     * This method adds a wanted patient to doctor's patients list
     * @param newPatient any patient
     * @return true if the adding process was successful
     */
    public boolean assignPatient( Patient newPatient )
    {
        boolean check = false;
        patients.add(newPatient);
        
        if ( patients.contains( newPatient ))
            check = true;
        
        return check;
    }
    
    /**
     * This method removes a wanted patient from doctor's patients list
     * @param oldTask any patient in the list
     * @return true if the removing process was successful
     */
    public boolean unassignPatient( Patient oldPatient )
    {
        boolean check = true;
        patients.remove(oldPatient);
        
        if ( patients.contains( oldPatient ))
            check = false;
        
        return check;
    }
    
    /**
     * This method refers a wanted patient from the doctor's patients list to another doctor
     * @param p any patient in the dostor's list
     * @return true if the referring process was successful
     */
    public boolean referPatient( Patient p, Doctor d, boolean unnasign )
    {
        boolean check = false;
        
        if ( unnasign ) 
            d.getPatients().add( p );
        
        if ( d.getPatients().contains( p ) && !patients.contains( p ) )
            check = true;
            
        return check;
    }
    
     /**
     * This method adds a wanted appointment to doctor's schedule
     * @param a any appointment
     * @return true if the adding process was successful
     */
    public boolean addAppointment( Appointment a )
    {
        boolean check = false;
        Date day = a.getDateAndTime();
        ArrayList<Appointment> todaysApps = getDateAppointments( day );
        
        if ( !todaysApps.contains( a ) ) {
            getDateAppointments( day ).add(a);
            check = true;
        }
        
        return check;
    }
    
     /**
     * This method postpones a wanted appointment from doctor's schedule
     * @param a any appointment from the schedule, @param d the date to postpone to
     * @return true if the postponing process was successful
     */
    public boolean postponeAppointment( Appointment a, Date d )
    {
        boolean check = false;
        Date day = a.getDateAndTime();
        ArrayList<Appointment> todaysApps = getDateAppointments( day );
        
        if ( todaysApps.contains( a ) ) {
            getDateAppointments( day ).remove(a);
            getDateAppointments( d ).add(a);
            check = true;
        }
        
        return check;
    }
    
     /**
     * This method cancels a wanted appointment from doctor's schedule
     * @param a any appointment
     * @return true if the canceling process was successful
     */
    public boolean cancelAppointment( Appointment a )
    {
        boolean check = false;
        Date day = a.getDateAndTime();
        ArrayList<Appointment> todaysApps = getDateAppointments( day );
        
        if ( todaysApps.contains( a ) ) {
            getDateAppointments( day ).remove(a);
            check = true;
        }
        
        return check;
    }
    
    /**
     * This method gets the appointments of a wanted date from the doctor's schedule
     * @param d any date
     * @return the arraylist of appointments of the day
     */
    public ArrayList<Appointment> getDateAppointments( Date d )
    {
        //??
    }
    
    /**
     * This method gets the available hour interval for the doctor of a wanted date 
     * @param d any date
     * @return hour interval as String
     */
    public String getAvailableIntervals( Date d )
    {
        //??
    }
    
    /**
     * This method sends a document from the doctor to another doctor
     * @param d a choosen doctor to send the document, 
     * @return true if the sending process was successful
     */
    public boolean sendDocuments( Doctor d,  )
    {
        //??
    }
    
    /**
     * This method views an already sent document to the doctor
     * @param d any date
     * @return hour interval as String
     */
    public boolean viewDocument(  )
    {
        //??
    }
}
