import GeneralInfo.*;
import Person.*;
import Schedule.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class DoctorTester{
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // Doctor

        // constants
        final int M_EXIT = 0;
        final int M_SEE_PATIENT = 1;
        final int M_ADV_SEARCH = 2;
        final int M_PROFILE = 3;
        final int M_SCHEDULE = 4;

        final int M_SHOW_PATIENTS = 1;
        final int M_SEARCH_PATIENTS = 2;

        final int M_SEARCH_HOSPITALS = 1;
        final int M_SEARCH_DOCTORS = 2;
        final int M_SEARCH_DISEASES = 3;
        final int M_SEARCH_MEDICATION = 4;

        final int M_ADD_APP = 1;
        final int M_EDIT_CONTACT = 2;

        final int M_SHOW_SCHEDULE = 1;
        final int M_SHOW_DATE = 2;


        // properties
        Hospital hospital = new Hospital();
        Department department = new Department("Something");
        hospital.addDepartment(department);
        Doctor doctor = new Doctor("Deniz Yılmaz", "denizy@gmail.com", "kırılmazşifre1");
        department.addDocToDepartment(doctor);

        Patient patient1 = new Patient("Baran X", "baranx@yahoo.com", 2565);
        patient1.addDoctor(doctor);
        doctor.assignPatient(patient1);
        Consultation cons = new Consultation();
        cons.setDate(LocalDateTime.now());
        patient1.addConsultation(cons);

        int selection1;
        int selection2;

        do {
            // display menu
            System.out.println("\n Today's appointments: " + doctor.getDateAppointments(LocalDateTime.now()) +
                    "\n Options: \n"
                    + M_SEE_PATIENT + ") See or add patient \n"
                    + M_ADV_SEARCH + ") Advanced search \n"
                    + M_PROFILE + ") See my profile \n"
                    + M_SCHEDULE + ") See my schedule \n"
                    + M_EXIT + ") Exit \n");

            selection1 = select();

            // process selection
            switch (selection1){
                case M_SEE_PATIENT:
                    // second menu
                    do {
                        System.out.println(
                                M_SHOW_PATIENTS + ") Show patients \n"
                                        + M_SEARCH_PATIENTS + ") Search patients \n"
                                        + M_EXIT + ") Exit \n");
                        selection2 = select();

                        switch (selection2){
                            case M_SHOW_PATIENTS:
                                for (Patient p : doctor.getPatients()) {
                                    System.out.print(p.getName());
                                    if(p.getLastConsultation() != null)
                                        System.out.println(p.getLastConsultation().getDate());
                                    else
                                        System.out.println();
                                }
                                break;
                            case M_SEARCH_PATIENTS:
                                break;
                            case M_EXIT:
                                break;
                            default:
                                System.out.println("Unknown selection");
                                break;
                        }
                    } while (selection2 != M_EXIT);
                    break;
                case M_ADV_SEARCH:
                    // second menu
                    do {
                        System.out.println(
                                M_SEARCH_HOSPITALS + ") Search hospitals \n"
                                        + M_SEARCH_DOCTORS + ") Search doctors \n"
                                        + M_SEARCH_DISEASES + ") Search diseases \n"
                                        + M_SEARCH_MEDICATION + ") Search medication \n"
                                        + M_EXIT +") Exit \n");
                        selection2 = select();

                        switch (selection2){
                            case M_SEARCH_HOSPITALS:
                                break;
                            case M_SEARCH_DOCTORS:
                                break;
                            case M_SEARCH_DISEASES:
                                break;
                            case M_SEARCH_MEDICATION:
                                break;
                            case M_EXIT:
                                break;
                            default:
                                System.out.println("Unknown selection");
                                break;
                        }
                    } while (selection2 != M_EXIT);
                    break;
                case M_PROFILE:
                    // second menu
                    do {
                        System.out.println(
                                M_ADD_APP + ") Add an appointment \n"
                                        + M_EDIT_CONTACT + ") Edit contact information \n"
                                        + M_EXIT + ") Exit \n");
                        selection2 = select();

                        switch (selection2){
                            case M_ADD_APP:
                                break;
                            case M_EDIT_CONTACT:
                                break;
                            case M_EXIT:
                                break;
                            default:
                                System.out.println("Unknown selection");
                                break;
                        }
                    } while (selection2 != M_EXIT);
                    break;
                case M_SCHEDULE:
                    // second menu
                    do {
                        System.out.println(
                                M_SHOW_SCHEDULE + ") Show the next ten days' schedule \n"
                                        + M_SHOW_DATE + ") Show a specific day's schedule \n"
                                        + M_EXIT + ") Exit \n");
                        selection2 = select();

                        switch (selection2){
                            case M_SHOW_SCHEDULE:
                                break;
                            case M_SHOW_DATE:
                                break;
                            case M_EXIT:
                                break;
                            default:
                                System.out.println("Unknown selection");
                                break;
                        }
                    } while (selection2 != M_EXIT);
                    break;
                case M_EXIT:
                    break;
                default:
                    System.out.println("Unknown selection");
                    break;
            }
        } while (selection1 != M_EXIT);
        System.out.println("End.");
    }

    public static int select(){
        System.out.println("Selection: ");
        int selection = scan.nextInt();
        scan.nextLine();
        return selection;
    }
}