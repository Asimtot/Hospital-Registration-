package Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Schedule.*;
import GeneralInfo.*;

import javax.persistence.Column;
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
public class Patient extends Person {
    // properties
    @OneToOne
    @JoinColumn(name = "GeneralInfo_id")
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
    
    @Column(name = "inIcu")
    private boolean inIcu;

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
    private Hospital hospital; // TODO Potential problem here (a patient may go to multiple hospitals)

    //TODO Database
    private Hospital icuHospital;

    // constructors
    public Patient(){
        doctors = new ArrayList<Doctor>();
        appointment = new ArrayList<Appointment>();
        activeDiseases = new ArrayList<Disease>();
    }
    // simple - just initializes
    public Patient(String name, String email){
        super(name, email);
        
        info = new GeneralInfo();
        doctors = new ArrayList<Doctor>();
        appointment = new ArrayList<Appointment>();
        address = new Address();
        inIcu = false;
        activeDiseases = new ArrayList<Disease>();
    }

    // complete constructor - if something does not exist, put null (?)
    public Patient(String name, String email, boolean inIcu, Hospital icuHospital, String city, String country, String address, Disease[] diseases){
        super(name, email);
        
        this.inIcu = inIcu;
        if(inIcu && icuHospital != null){
            this.icuHospital = icuHospital;
        }

        // if any one address field is not null, then create an instance of Address
        if (city != null || country != null || address != null){
            this.address = new Address(city, country, address);
        }

        doctors = new ArrayList<Doctor>();
       // addDoctor(Booting.getUser); // user must be the patient's doctor//TODO implement booting

        appointment = new ArrayList<Appointment>(); // no way to add appointments in the patient creation screen

        activeDiseases = new ArrayList<Disease>();
        Collections.addAll(activeDiseases, diseases);

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
        return activeDiseases;
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

    public boolean setInIcu(boolean inIcu, Hospital icuHospital) {
        this.inIcu = inIcu;

        if (inIcu)
            return icuHospital.assignPatientToIcu(this);
        else if (icuHospital != null)
            return icuHospital.unassignPatientFromIcu(this);
        else
            // if the icuHospital is null (i.e. if the patient was never in ICU)  return true;
            return true;
    }
    /**
     * This method is only for the Hospital class to use
     */
    void setInIcu(boolean inIcu){
        this.inIcu = inIcu;
    }

}

