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
    List<Patient> patients= new ArrayList<>();
    //TODO patients dan icuPatients ve NormalPatients arrayListi yaratan method
    @Transient
    private ArrayList<Patient> icuPatients= new ArrayList<>();
    @Transient
    private ArrayList<Patient> normalPatients= new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "Adress_id")
    private Address address;
    
    //TODO İcuOccupancy ve normalOccupancy hesaplayan metodlar
    @Column(name = "icuCapacity")
    private int icuCapacity;
    @Transient
    private int icuOccupancy;
    @Column(name= "normalCapacity")
    private int normalCapacity;
    @Transient
    private int normalOccupancy; //Occupied bed on the hospital other than icu beds
    
    
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
        hospitalDoctors = new ArrayList<Doctor>();
        icuOccupancy = 0;
        normalOccupancy = 0;

    }
    public Hospital( String hospitalName, int icuCapacity, int normalCapacity){
        this.hospitalName = hospitalName;
        departments = new ArrayList<Department>();
        hospitalDoctors = new ArrayList<Doctor>();

        this.icuCapacity = icuCapacity;
        this.normalCapacity = normalCapacity;


        icuOccupancy = 0;
        normalOccupancy = 0;

    }


    //getters
    public Address getAdress() {
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
    public int getNormalCapacity() {
        return normalCapacity;
    }
    public int getOccupancy() {
        return normalOccupancy;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }


    //setters
    public void setIcuCapacity( int capacity){
        icuCapacity = capacity;

    }
    public void setNormalCapacity( int capacity){
        normalCapacity = capacity;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //methods

    /**
     *
     * @param p
     * @return
     */
    public boolean assignPatientToIcu( Patient p) {
        if(icuOccupancy < icuCapacity) {
            p.setInICU(true);
            icuOccupancy++;
            return true;
        }
        return false;

    }

    /**
     *
     * @param p
     * @return
     */
    public boolean assignPatientToNormalBeds(Patient p){
        if( normalOccupancy < normalCapacity ){
            p.setInICU(true);
            normalOccupancy++;
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

    /**
     *
     * @return
     */
    public double calculateNormalBedOccupancy(){
        return ( (double) normalOccupancy /(double) normalCapacity) * 100;
    }

    
    
}
