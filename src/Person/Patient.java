package Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Schedule.*;
import GeneralInfo.*;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "Patient")
public class Patient extends Person{
    // properties

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneralInfo_id")
    private GeneralInfo info;

    @ManyToMany
    @JoinTable(name = "DoctorPatientJoin",
                joinColumns = @JoinColumn(name= "Patient_id"),
                inverseJoinColumns= @JoinColumn(name = "Doctor_id"))
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointment;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Address_id")
    private Address address;
    
    @Column(name = "inIcu")
    private boolean inICU;

    @ManyToMany
    @JoinTable(name = "PatientDiseaseJoin",
                        joinColumns = @JoinColumn(name= "Patient_id"),
                        inverseJoinColumns = @JoinColumn(name= "Disease_id"))
    private List<Disease> activeDiseases;

    //DATABASE için gerekliler
    @OneToOne(mappedBy = "owner")
    private Body body;

    @ManyToOne
    @JoinColumn(name = "Hospital_id")
    private Hospital hospital;

    // constructors
    public Patient(){}
    // simple - just initializes
    public Patient(String name, String email){
        super(name, email);
        
        //info = new GeneralInfo();
        doctors = new ArrayList<Doctor>();
        appointment = new ArrayList<Appointment>();
        //address = new Address();
        inICU = false;
        activeDiseases = new ArrayList<Disease>();
    }

    // complete constructor - if something does not exist, put null (?)
    public Patient(String name, String email, boolean inICU, String city, String country, String address, ArrayList<Disease> diseases){
        super(name, email);
        
        this.inICU = inICU;

        // if any one address field is not null, then create an instance of Address
        if (city != null || country != null || address != null){
            this.address = new Address(city, country, address);
        }

        doctors = new ArrayList<Doctor>();
       // addDoctor(Booting.getUser); // user must be the patient's doctor//TODO implement booting

        appointment = new ArrayList<Appointment>(); // no way to add appointments in the patient creation screen

        activeDiseases = new ArrayList<Disease>();
        //TODO burada noluyor
        //Collections.addAll(activeDiseases, diseases);
        

        info= new GeneralInfo();
        // ^^ should initialize empty body and consultations
        // ^^ should check if any Patient is null first
    }

    // methods

    public void addConsultation(Consultation consultation){
        info.addConsultation(consultation);
    }

    public Consultation getLastConsultation(){
        return info.getLastConsultation();
    }

    public boolean isPatientOf(Doctor doctor){
        return doctors.contains(doctor);
    }

    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }

    public void addActiveDisease(Disease disease){ // was boolean in the UML
        activeDiseases.add(disease);
    }

    public boolean removeActiveDisease(Disease disease){ // was boolean in the UML
        return activeDiseases.remove(disease);
    }

    public void drawBody(){
        //info.drawBody()
        //TODO drawBody method
    }

    // *** getters ***

    public Address getAddress() {
        return address;
    }

    public ArrayList<Appointment> getAppointment() {
        return (ArrayList<Appointment>) appointment;
    }

    public ArrayList<Disease> getActiveDiseases() {
        return (ArrayList<Disease>) activeDiseases;
    }

    public ArrayList<Doctor> getDoctors() {
        return (ArrayList<Doctor>) doctors;
    }

    public GeneralInfo getInfo() {
        return info;
    }

    public int getID() {
        return super.id;
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

   

    public void setInfo(GeneralInfo info) {
        this.info = info;
    }

    public void setInICU(boolean inICU) {
        this.inICU = inICU;
    }

  
    public String showSendable() { //FIXME this is temporary
        return "Patient{" +
                "ID=" + id +
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

