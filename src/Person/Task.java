package Person;

/**
 * Task class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/ 
public class Task {
    
    // Properties
    
    private String name;
    private Doctor sender;
    private String fileName;
    
    // Constructor

    public Task(){}
    
    public Task ( String name, Doctor sender, String fileName )
    {
        this.name = name;
        this.sender = sender;
        this.fileName = fileName;
    }
    
    // Methods
    
    public String getName()
    {
        return name;
    }
   
    public Doctor getSender()
    {
        return sender;
    }
    
    public String getFileName()
    {
        return fileName;
    }

    // setters


    public void setName(String name) {
        this.name = name;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSender(Doctor sender) {
        this.sender = sender;
    }
}
