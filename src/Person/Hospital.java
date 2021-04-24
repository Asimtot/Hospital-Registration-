package Person;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import GeneralInfo.*;

/**
 * Hospital Class
 * @author Yusuf Doğan
 * @version 20/04/2021
 */
public class Hospital {

    //properties
    private String hospitalName;
    private List<Department> departments;
    private List<Doctor> hospitalDoctors;
    private Patient[] icuPatients;
    private Patient[] normalPatients;
    private int icuOccupancy;
    private int normalOccupancy; //Occupied bed on the hospital other than icu beds
    private int normalCapacity;
    private int icuCapacity;
    private Address adress;
    private String phoneNumber;
    private Logo logo;

    //constructors
    public Hospital(){
        hospitalDoctors = new ArrayList<>();
        icuOccupancy = 0;
        normalOccupancy = 0;
        departments = new ArrayList<>();

    }
    public Hospital( String hospitalName, int icuCapacity, int normalCapacity){
        this.hospitalName = hospitalName;
        departments = new ArrayList<Department>();
        hospitalDoctors = new ArrayList<Doctor>();

        this.icuCapacity = icuCapacity;
        this.normalCapacity = normalCapacity;
        icuPatients = new Patient[icuCapacity];
        normalPatients = new Patient[normalCapacity];
        icuOccupancy = 0;
        normalOccupancy = 0;

    }


    //getters
    public Address getAdress() {
        return adress;
    }
    public ArrayList<Department> getDepartments() {
        return (ArrayList<Department>) departments;
    }
    public ArrayList<Doctor> getHospitalDoctors() {
        return (ArrayList<Doctor>) hospitalDoctors;
    }
    public Patient[] getIcuPatients() {
        return icuPatients;
    }
    public Patient[] getNormalPatients() {
        return normalPatients;
    }
    public Logo getLogo() {
        return logo;
    }
    public int getIcuCapacity() {
        return icuPatients.length;
    }
    public int getNormalCapacity() {
        return normalCapacity;
    }
    public int getOccupancy() {
        return normalPatients.length;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }


    //setters
    public void setIcuCapacity( int capacity){
        icuCapacity = capacity;
        icuPatients = new Patient[icuCapacity];

    }
    public void setNormalCapacity( int capacity){
        normalCapacity = capacity;
        normalPatients = new Patient[ normalCapacity];
    }
    public void setAddress(Address adress) {
        this.adress = adress;
    }
    public void setLogo(Logo logo) {
        this.logo = logo;
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
            icuPatients[icuOccupancy] = p;
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
            normalPatients[normalOccupancy] = p;
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

    public void incrementIcuCapacity( int incrementPercentage){
        int newCapacity = icuCapacity +(int)((double)icuCapacity * (double) incrementPercentage / 100);
        Patient[] ptnt = new Patient[newCapacity];
        int j=0;

        //transferring patients from small array to larger one
        for ( int i = 0; i < getIcuPatients().length ; i++){
            if( getIcuPatients()[i] != null){
                ptnt[j] = getIcuPatients()[i];
                j++;
            }
        }
        this.icuCapacity = newCapacity;
        this.icuPatients = ptnt;
    }
    public void incrementNormalBedCapacity( int incrementPercentage){
        int newCapacity = normalCapacity +(int)((double)normalCapacity * (double) incrementPercentage / 100);
        Patient[] ptnt = new Patient[newCapacity];
        int j =0;

        //transferring patients from small array to larger one
        for ( int i = 0; i < getNormalPatients().length ; i++){
            if( getNormalPatients()[i] != null){
                ptnt[j] = getNormalPatients()[i];
                j++;
            }
        }
        this.normalCapacity = newCapacity;
        this.normalPatients = ptnt;

    }
}
