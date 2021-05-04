package GeneralInfo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import Person.*;

/**
 * Adress class for properties (Hospitals )
 * @author Emre Uğur
 *
 */

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "city")
    String city;
    @Column(name= "county")
    String county;
    @Column(name = "address")
    String address;

    //DATABASE için gerekli
    @OneToMany(mappedBy = "address")
    List<Patient> patients= new ArrayList<>();

    @OneToOne(mappedBy= "address")
    Hospital hospital;


    // constructors
    public Address(){}

    public Address(String city, String county, String address) {
        this.city = city;
        this.county = county;
        this.address = address;
    }

    public Address(String address) {
        this.address = address;
    }

    // getters

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }


// setters


    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
