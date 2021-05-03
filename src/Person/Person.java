package Person;
import java.util.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


import javax.persistence.OneToMany;
import javax.persistence.Table;

import GeneralInfo.*;


/**
 * Person class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Person")
public class Person {
    
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name= "telNo")
    private String telNo;
    
    @OneToMany(mappedBy = "receiver")
    private List<Notification> notifications;

    //DATABASE için gerekli
    
    
    @OneToMany(mappedBy = "sender")
    List<Notification> sendNotifications;

    // Constructor

    public Person(){}

    public Person ( String name, String email,String telNo)
    {
        this.name = name;
        this.email = email;
        this.telNo= telNo;

        notifications = new ArrayList<Notification>();
    }


    public Person ( String name, String email, String password ,String telNo)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.telNo= telNo;
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
    
    public ArrayList<Notification> getNotifications()
    {
        return (ArrayList<Notification>) notifications;
    }
    
    /**
     * This method sends a notification to a chosen person of any type
     * @param p person to send the notification, @param n the notification to send
     * @return true if the sending process was successful
     */
    public boolean sendNotification( Person p, Notification n )
    {
        boolean check = false;
        p.getNotifications().add(n);
        
        if ( p.getNotifications().contains(n))
            check = true;
        
        return check;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRandomPassword(){
        password = createRandomPassword(12);
    }

    public void setRandomPassword(int length){
        password = createRandomPassword(length);
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public static String createRandomPassword(int length){
        final char[] CHARS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','j','k',
                'l','m','n','o', 'p','q','r','s','t','u','v','w','x','y','z', 'A','B','C','D','E','F','G','H','J',
                'K','L','M', 'N','O','P','Q','R','S','T','U','V','W','X','Y','Y','Z'};

        int random;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            random = (int)(Math.random() * CHARS.length);
            sb.append(CHARS[random]);
        }

        return sb.toString();
    }
    
}

