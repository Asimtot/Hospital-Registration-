package Person;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Task class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/
@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    // Properties
    @Column(name= "name")
    private String name;

    @Column(name = "done")
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "Doctor_id")
    private Doctor receiver;

    @ManyToOne
    @JoinColumn(name = "SenderDoctor_id")
    private Doctor sender;
    @Transient //TODO
    private String fileName;
    
    // Constructor

    public Task(){}

    public Task(String name, Doctor receiver, Doctor sender, boolean done){
        this.name = name;
        this.receiver = receiver;
        this.sender = sender;
        this.done = done;
    }
    
    public Task ( String name, Doctor receiver, Doctor sender, String fileName, boolean done )
    {
        this.name = name;
        this.receiver = receiver;
        this.sender = sender;
        this.fileName = fileName;
        this.done = done;
    }
    
    // Methods

    public void send(){
        receiver.addTask(this);
    }
    
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

    public boolean getDone(){return done;}

    // setters


    public void setName(String name) {
        this.name = name;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setReciever(Doctor receiver) {
        this.receiver = receiver;
    }

    public void setSender(Doctor sender) {
        this.sender = sender;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
