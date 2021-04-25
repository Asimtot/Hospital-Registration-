package Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Schedule.*;
import GeneralInfo.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "Patient.id")
@Table(name = "Patient")
public class Patient extends Person implements Sendable{
    // properties
  
    private GeneralInfo info;

    @ManyToMany
    @JoinTable(name = "DoctorPatientJoin",
                joinColumns = @JoinColumn(name= "Patient_id"),
                inverseJoinColumns= @JoinColumn(name = "Doctor_id"))
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointment;
    
    @ManyToOne
    @JoinColumn(name = "Adress_id")
    private Address address;
    
    private boolean inICU;

    @ManyToMany
    @JoinTable(name = "PatientDiseaseJoin",
                        joinColumns = @JoinColumn(name= "Patient_id"),
                        inverseJoinColumns = @JoinColumn(name= "Disease_id"))
    private ArrayList<Disease> activeDiseases;

    //DATABASE için gerekliler
    @OneToOne(mappedBy = "owner")
    private Body body;

    @ManyToOne
    @JoinColumn(name = "Hospital_id")
    private Hospital hospital;

    // constructors

    public Patient(){
        doctors = new ArrayList<Doctor>();
        appointment = new ArrayList<Appointment>();
        activeDiseases = new ArrayList<Disease>();
    }
    // simple - just initializes
    public Patient(String name, String email, int ID){
        super(name, email); // FIXME Person class should have createRandomPassword and showPassword methods
        this.ID = ID;
        info = new GeneralInfo();
        doctors = new ArrayList<Doctor>();
        appointment = new ArrayList<Appointment>();
        address = new Address();
        inICU = false;
        activeDiseases = new ArrayList<Disease>();
    }

    // complete constructor - if something does not exist, put null (?)
    public Patient(String name, String email, int ID, boolean inICU, String city, String country, String address, Disease[] diseases,
                   Patient mother, Patient father, Patient partner, Patient[] siblings, Patient[] children){
        super(name, email);
        this.ID = ID;
        this.inICU = inICU;

        // if any one address field is not null, then create an instance of Address
        if (city != null || country != null || address != null){
            this.address = new Address(city, country, address); // FIXME check Address constructor
        }

        doctors = new ArrayList<Doctor>();
        addDoctor(Booting.getUser); // user must be the patient's doctor

        appointment = new ArrayList<Appointment>(); // no way to add appointments in the patient creation screen

        activeDiseases = new ArrayList<Disease>();
        Collections.addAll(activeDiseases, diseases);

        info = new GeneralInfo(mother, father, partner, siblings, children); // FIXME check GeneralInfo constructor
        // ^^ should initialize empty body and consultations
        // ^^ should check if any Patient is null first
    }

    // methods

    public void addConsultation(Consultation consultation){
        info.addConsultation(consultation); //FIXME add this method to generalInfo (boolean?)
    }

    public Consultation getLastConsultation(){
        return info.getLastConsultation(); //FIXME add this method to generalInfo
    }

    public boolean isPatientOf(Doctor doctor){
        return doctors.contains(doctor);
    }

    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }

    // FIXME seeNotifications should be in the Person class (?)

    public void addActiveDisease(Disease disease){ // was boolean in the UML
        activeDiseases.add(disease);
    }

    public boolean removeActiveDisease(Disease disease){ // was boolean in the UML
        return activeDiseases.remove(disease);
    }

    public void drawBody(){
        info.drawBody();
    }

    // *** getters ***

    public Address getAddress() {
        return address;
    }

    public ArrayList<Appointment> getAppointment() {
        return (ArrayList<Appointment>) appointment;
    }

    public ArrayList<Disease> getActiveDiseases() {
        return activeDiseases;
    }

    public ArrayList<Doctor> getDoctors() {
        return (ArrayList<Doctor>) doctors;
    }

    public GeneralInfo getInfo() {
        return info;
    }

    public int getID() {
        return ID;
    }

    // *** setters ***

    public void setAddress(Address address) {
        this.address = address;
    }

    // setAppointment method should not exist as it may potentially clash with the doctor's schedule

    public void setActiveDiseases(ArrayList<Disease> activeDiseases) {
        this.activeDiseases = activeDiseases;
    }

    public void setDoctors(ArrayList<Doctor> doctors) { // when will this be used?
        this.doctors = doctors;
    }

    public void setID(int ID) { // should it be allowed?
        this.ID = ID;
    }

    public void setInfo(GeneralInfo info) {
        this.info = info;
    }

    public void setInICU(boolean inICU) {
        this.inICU = inICU;
    }

    @Override
    public String showSendable() { //FIXME this is temporary
        return "Patient{" +
                "ID=" + ID +
                ", name=" + getName() +
                ", info=" + info +
                ", doctors=" + doctors +
                ", appointment=" + appointment +
                ", address=" + address +
                ", inICU=" + inICU +
                ", activeDiseases=" + activeDiseases +
                '}';
    }
}

