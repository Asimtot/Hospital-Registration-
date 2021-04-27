import GeneralInfo.*;
import Person.*;
import Schedule.*;

public class HospitalTester {
    public static void main(String[] args) {

        //variables
        Hospital hospital = new Hospital("Bilkent Health Center", 10,20);

        Patient p1= new Patient();
        Patient p2= new Patient();
        Patient p3= new Patient();
        Patient p4= new Patient();
        Patient p5= new Patient();

        Department cardiology = new Department("Cardiology");
        Department orthopedics  = new Department("Orthopedics");
        Department generalSurgery = new Department("General Surgery");

        Doctor d1 = new Doctor();
        Doctor d2 = new Doctor();
        Doctor d3 = new Doctor();
        Doctor d4 = new Doctor();
        Doctor d5 = new Doctor();

        cardiology.addDocToDepartment(d1);
        orthopedics.addDocToDepartment(d2);
        generalSurgery.addDocToDepartment(d3);

        hospital.addDepartment(cardiology);
        hospital.addDepartment(generalSurgery);

        hospital.addDoctor(d4, cardiology);
        hospital.addDoctor(d5, orthopedics); // it should return false since orthopedics is not added to hospital yet

        hospital.assignPatientToIcu(p1);
        hospital.assignPatientToIcu(p2);
        hospital.assignPatientToIcu(p3);

        hospital.assignPatientToNormalBeds(p4);
        hospital.assignPatientToNormalBeds(p5);




        System.out.println(hospital.getIcuCapacity());
        System.out.println(hospital.getNormalCapacity());
        System.out.println(hospital.calculateIcuOccupancyPercentage());
        System.out.println(hospital.calculateNormalBedOccupancy());

        hospital.incrementIcuCapacity(50);
        hospital.incrementNormalBedCapacity(50);

        System.out.println("-------");
        System.out.println(hospital.getIcuCapacity());
        System.out.println(hospital.getNormalCapacity());
        System.out.println(hospital.calculateIcuOccupancyPercentage());
        System.out.println(hospital.calculateNormalBedOccupancy());

        System.out.println();


        Tree d1Tree = new Tree( d1);
        d1Tree.addPartner(d2);
        Person Ayşe = new Person();
        Person Rıza = new Person();

        d1Tree.addFather(Rıza);
        d1Tree.addMother(Ayşe);

        Tree ayşeTree = new Tree(p5);

        System.out.println(ayşeTree.isRelative(d1Tree));

    }
}
