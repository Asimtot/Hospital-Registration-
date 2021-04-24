package GeneralInfo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Person.*;


@Entity
@Table(name = "Adress")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //DATABASE için gerekli
    @OneToMany(mappedBy = "adress")
    List<Patient> patients= new ArrayList<>();

    //TODO
}
