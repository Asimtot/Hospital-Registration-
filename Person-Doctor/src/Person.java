import java.util.*;

/**
 * Person class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/ 
public class Person {
    
    // Properties
    
    private String name;
    private String email;
    private String password;
    private ArrayList<Notification> notifications;
    
    // Constructor
    
    public Person ( String name, String email, String password )
    {
        this.name = name;
        this.email = email;
        this.password = password;
        notifications = new ArrayList<Notification>();
    }
    
    // Methods
    
    public String getName()
    {
        return name;
    }
   
    public String getEmail()
    {
        return email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public ArrayList<Notification> getNot()
    {
        return notifications;
    }
    
    /**
     * This method sends a notification to a chosen person of any type
     * @param p person to send the notification, @param n the notification to send
     * @return true if the sending process was successful
     */
    public boolean sendNotification( Person p, Notification n )
    {
        boolean check = false;
        p.getNot().add(n);
        
        if ( p.getNot().contains(n))
            check = true;
        
        return check;
    }
    
}

