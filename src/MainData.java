import Database.Database;
import GeneralInfo.Address;
import GeneralInfo.Consultation;
import GeneralInfo.GeneralInfo;
import Person.*;
import Schedule.Appointment;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class MainData {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();

//        Doctor doctor1 = new Doctor("Deniz Yılmaz", "fkfkkf", "dkdkdkd");
//        Doctor doctor2 = new Doctor("Gamze Yılmaz", "fkfkkf", "dkdkdkd");
        //Doctor doctor3 = new Doctor("Okan Yılmaz", "fkfkkf", "dkdkdkd");
//        Doctor doctor4 = new Doctor("Tekman Yılmaz", "fkfkkf", "dkdkdkd");
//        Doctor doctor5 = new Doctor("Selçuk Yılmaz", "fkfkkf", "dkdkdkd");
//
//        Patient patient1 = new Patient("Selin Gündüz", "jddkdkdk");
//        Patient patient2 = new Patient("Ali Gündüz", "jddkdkdk");
//        Patient patient3 = new Patient("Berk Gündüz", "jddkdkdk");
//        Patient patient4 = new Patient("Buse Gündüz", "jddkdkdk");
//
//        Hospital hospital = new Hospital("FirstHospital", 22);
//        Department department1 = new Department("Cardiology");
//        Department department2 = new Department("Oncology");
//        Department department3 = new Department("Dermatology");
//
//        hospital.addDepartment(department1);
//        hospital.addDepartment(department2);
//        hospital.addDepartment(department3);
//
//        hospital.addDoctor(doctor1, department1);
//        hospital.addDoctor(doctor2, department1);
//        hospital.addDoctor(doctor3, department2);
//        hospital.addDoctor(doctor4, department2);
//        hospital.addDoctor(doctor5, department3);
//        doctor1.addAppointment(appointment);
//
//        doctor1.assignPatient(patient1);
//        doctor1.assignPatient(patient2);
//
//        Task task1 = new Task("Do this", doctor1,doctor1,"kk",true);
//        Task task2 = new Task("Do that", doctor1,doctor1,"jj", false);
//
//        doctor1.addTask(task1);
//        doctor1.addTask(task2);

//        Doctor doctor = database.getDoctor("Deniz Yılmaz");
//        Patient patient = database.getPatient("Selin Gündüz");
//        Appointment appointment = new Appointment("App", doctor, patient, doctor.getHospital(), doctor.getDepartment(), 50, 2021,5,2,9,30);
//
//
//        Appointment appointment1 = database.getAppointment(5);
//        System.out.println(appointment1.getStartingTime());
//        appointment1.calculateEndingTime();
//        doctor.addAppointment(appointment1);
//        appointment.setDoctor(doctor);
//
//        System.out.println(doctor.getDateAppointments(LocalDateTime.of(2021,5,2,0,0)).get(0).getName());
//        database.update(doctor);
//        database.update(appointment1);

        database.deleteDoctor("Deniz Yılmaz");

    }
}

