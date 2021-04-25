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

import Person.*;

@Entity
@Table(name = "Notification")
class Notification{


    // properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    int id;
    
    LocalDateTime date;
    String text;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    Person sender;

    @ManyToOne
    @JoinColumn(name = "reciever_id")
    Person receiver;
    
    Sendable sendable;

    public Notification(){}

    public Notification(LocalDateTime date, String text, Person sender, Person receiver, Sendable sendable) {
        this.date = date;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.sendable = sendable;
    }

    public String show(){
        return sendable.showSendable();
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

    public Sendable getSendable() {
        return sendable;
    }

    public String getText() {
        return text;
    }
// setters


    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public void setSendable(Sendable sendable) {
        this.sendable = sendable;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }
}