package Person;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

import GeneralInfo.*;
import Schedule.Appointment;

/**
 * Hospital Class
 * @author Yusuf Doğan
 * @version 20/04/2021
 */
@Entity
@Table(name = "Hospital")
public class Hospital {

    //properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    private String hospitalName;

    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private List<Department> departments;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> hospitalDoctors;

    @OneToMany(mappedBy = "icuHospital")
    List<Patient> icuPatients= new ArrayList<>(); // needed?

    @OneToOne
    @JoinColumn(name = "Address_id")
    private Address address;

    @Column(name = "icuCapacity")
    private int icuCapacity;
    @Transient
    private int icuOccupancy;
    
    @Column(name= "phoneNumber")
    private String phoneNumber;
    @Transient//şimdilik db e eklemedim
    private String logo;

    //DATABASE için gerekli
    @OneToMany(mappedBy = "place")
    private List<Appointment> appointments;

    @OneToOne(mappedBy = "hospital")
    private Admin admin;

    //constructors
    public Hospital(){
        departments = new ArrayList<Department>();
        hospitalDoctors = new ArrayList<Doctor>();
        icuPatients = new ArrayList<Patient>();
        icuOccupancy = 0;

    }//TODO patients dan icuPatients ve NormalPatients arrayListi yaratan method
    public Hospital( String hospitalName, int icuCapacity){
        this.hospitalName = hospitalName;
        departments = new ArrayList<Department>();
        hospitalDoctors = new ArrayList<Doctor>();
        icuPatients = new ArrayList<Patient>();

        this.icuCapacity = icuCapacity;
        icuOccupancy = 0;
    }


    //getters
    public Address getAddress() {
        return address;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    
    public List<Doctor> getHospitalDoctors() {
        return hospitalDoctors;
    }
    
    public int getIcuCapacity() {
        return icuCapacity;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getIcuOccupancy() {
        return icuOccupancy;
    }

    public ArrayList<Patient> getIcuPatients() {
        return (ArrayList<Patient>) icuPatients;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public int getId() {
        return id;
    }

    //setters
    public void setIcuCapacity( int capacity){
        icuCapacity = capacity;

    }
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //methods

    public ArrayList<Patient> getAllPatients(){
        ArrayList<Patient> patients = new ArrayList<Patient>();
        for(Doctor doctor : hospitalDoctors){
            patients.addAll(doctor.getPatients());
        }
        return patients;
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean assignPatientToIcu( Patient p) {
        if(icuOccupancy < icuCapacity) {

            p.privateSetInIcu(true,this);
            icuPatients.add(p);
            icuOccupancy++;
            return true;
        }
        return false;
    }
    //TODO 
    public boolean unassignPatientFromIcu(Patient p){
        if(icuPatients.remove(p)){
            icuOccupancy--;
            p.privateSetInIcu(false,this);
            return true;
        }
        return false;
    }


    /**
     *
     * @param d
     */
    public void addDepartment( Department d){
        departments.add(d);
        d.setHospital(this);

    }
    /**
     *
     * @param doctor
     * @param dp
     */
    public boolean addDoctor(Doctor doctor, Department dp ){
        if( departments.contains(dp)) {
            hospitalDoctors.add(doctor);
            dp.addDocToDepartment(doctor);
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public double calculateIcuOccupancyPercentage(){
        return ( (double) icuOccupancy / (double) icuCapacity ) * 100;
    }

    
    
}
