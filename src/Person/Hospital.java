package Person;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import GeneralInfo.*;
import Schedule.Appointment;

/**
 * Hospital Class
 * @author Yusuf Doğan
 * @version 20/04/2021
 */
public class Hospital {

    //properties
    @Column(name = "name")
    private String hospitalName;

    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> hospitalDoctors;

    @OneToMany(mappedBy = "hospital")
    List<Patient> patients= new ArrayList<>(); // needed?

    @Transient
    private List<Patient> icuPatients;

    @OneToOne
    @JoinColumn(name = "Adress_id")
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
    }
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
    public ArrayList<Department> getDepartments() {
        return (ArrayList<Department>) departments;
    }
    public ArrayList<Doctor> getHospitalDoctors() {
        return (ArrayList<Doctor>) hospitalDoctors;
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
            p.setInIcu(true);
            icuPatients.add(p);
            icuOccupancy++;
            return true;
        }
        return false;
    }

    public boolean unassignPatientFromIcu(Patient p){
        if(icuPatients.remove(p)){
            icuOccupancy--;
            p.setInIcu(false);
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

        //adding doctors of department  to hospital doctor list
        ArrayList<Doctor> dDoctors = d.getDepartmentDoctors();
        for( int i = 0; i < dDoctors.size(); i++){
            hospitalDoctors.add(dDoctors.get(i));
        }
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
