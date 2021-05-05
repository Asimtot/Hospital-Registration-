package GeneralInfo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import Person.*;
import Schedule.Converter;

/**
 * Notication class that enable communication and informing between users
 * @author Emre Uğur
 */
@Entity
@Table(name = "Notification")
public class Notification{


    // properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    int id;
    
    @Column(name = "date")
    String dateStr; //YYYY-MM-DD hh:mm:ss[.nnn] (SQL dateTime format)
    @Transient
    LocalDateTime date;

    @Column(name = "text")
    String text;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    Person sender;

    @ManyToOne
    @JoinColumn(name = "reciever_id")
    Person receiver;
    
    
    

    public Notification(){}

    public Notification(LocalDateTime date, String text, Person sender, Person receiver) {
        this.date = date;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        
    }

    public String show(){
        //return sendable.showSendable();//TODO
        return "";
    }

    // getters

    public LocalDateTime getDate() {
        return date;
    }

    public Person getReceiver() {
        return receiver;
    }

    public Person getSender() {
        return sender;
    }

    

    public String getText() {
        return text;
    }

    // setters

    public void setDate(){
    date = Converter.toLocalDateTime(dateStr);
}

    public void setDateStr(){
        dateStr = Converter.toString(date);
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }
}