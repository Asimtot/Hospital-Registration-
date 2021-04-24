package Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Task class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/ 
@Entity
@Table(name = "Task")
public class Task {
    
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "Doctor_id")
    private Doctor reciever;

    @ManyToOne
    @JoinColumn(name = "SenderDoctor_id")
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
