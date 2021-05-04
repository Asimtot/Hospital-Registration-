package Person;
import java.util.ArrayList;

import java.util.List;

import GUI.Helpers.UpdatedTable;
import GUI.MainGUI.frmDoctor;
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
    private GeneralInfo info = new GeneralInfo(this);

    @ManyToMany
    @JoinTable(name = "DoctorPatientJoin",
                joinColumns = @JoinColumn(name= "Patient_id"),
                inverseJoinColumns= @JoinColumn(name = "Doctor_id"))
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Address_id")
    private Address address;
    
    @Column(name = "inIcu")
    private boolean inIcu;

    @ManyToMany
    @JoinTable(name = "PatientDiseaseJoin",
                        joinColumns = @JoinColumn(name= "Patient_id"),
                        inverseJoinColumns = @JoinColumn(name= "Disease_id"))
    private List<Disease> activeDiseases;

    //DATABASE için gerekliler
    @OneToOne(mappedBy = "owner")
    private Body body;

    @Column(name= "sex")
    private String sex;


    @ManyToOne
    @JoinColumn(name = "Hospital_id")
    private Hospital icuHospital;

    // constructors
    public Patient(){
        doctors = new ArrayList<Doctor>();
        appointments = new ArrayList<Appointment>();
        address = new Address();
        inIcu = false;
        activeDiseases = new ArrayList<Disease>();
    }
    // simple - just initializes


    public Patient(String name, String email,String telNo,  String nationalId, String nationality,String sex){
        super(name, email,telNo, nationalId, nationality);

        doctors = new ArrayList<Doctor>();
        appointments = new ArrayList<Appointment>();
        address = new Address();
        inIcu = false;
        this.sex= sex;
        activeDiseases = new ArrayList<Disease>();
    }

    // complete constructor - if something does not exist, put null (?)
    public Patient(String name, String email, boolean inIcu, Hospital icuHospital, String city, String country, String address, Disease[] diseases,String telNo, String nationalId, String nationality, String sex){
        super(name, email,telNo,nationalId,nationality);
        this.sex= sex;
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

        appointments = new ArrayList<Appointment>(); // no way to add appointments in the patient creation screen

        activeDiseases = new ArrayList<Disease>();
        //TODO burada noluyor
        //Collections.addAll(activeDiseases, diseases);
        

        info= new GeneralInfo(this);
        // ^^ should initialize empty body and consultations
        // ^^ should check if any Patient is null first
    }

    // methods

    public void addConsultation(Consultation consultation) {
        info.addConsultation(consultation);
    }

    public Consultation getLastConsultation() { return info.getLastConsultation(); }
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

    public List<Appointment> getAppointment() {

        return (appointments);
    }

    public List<Disease> getActiveDiseases() {
        return activeDiseases;
    }

    public ArrayList<Doctor> getDoctors() {
        return (ArrayList<Doctor>)doctors;
    }

    public GeneralInfo getInfo() {
        return info;
    }

    public int getID() {
        return super.id;
    }
    public boolean getInIcu() {
        return inIcu;
    }

    public Hospital getIcuHospital() {
        return icuHospital;
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

        if (inIcu)
            return icuHospital.assignPatientToIcu(this);
        else if (icuHospital != null)
            return icuHospital.unassignPatientFromIcu(this);
        else{
            this.inIcu = inIcu;
            // if the icuHospital is null (i.e. if the patient was never in ICU)  return true
            return true;
        }

    }
    /**
     * This method is only for the Hospital class to use
     */
    void privateSetInIcu(boolean inIcu, Hospital icuHospital){
        this.inIcu = inIcu;
        if(inIcu)
            this.icuHospital = icuHospital;
        else
            icuHospital = null;
    }


  
    public String showSendable() { //FIXME this is temporary
        return "Patient{" +
                "ID=" + id +
                ", name=" + getName() +
                ", info=" + info +
                ", doctors=" + doctors +
                ", appointment=" + appointments +
                ", address=" + address +
                ", inICU=" + inIcu +
                ", activeDiseases=" + activeDiseases +
                '}';
    }
}

