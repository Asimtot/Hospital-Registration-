public class Medication {
    
    // TO_DO object for getting the medication info

    private String name;
    private Department department;
    private String info;
    private Disease[] dClashes;
    private Medication[] dMedications;

    public Medication(){

        department = new Department();

    }

    public String getName(){
        return name;
    }

    public Department getDepartment(){
        return department;
    }

    public String getInfo(){
        return info;
    }

    public Disease[] getDisease(){
        return dClashes;
    }

    public Medication[] getMedication(){
        return dMedications;
    }


}
