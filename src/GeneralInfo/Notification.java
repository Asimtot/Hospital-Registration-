package GeneralInfo;

import java.time.LocalDateTime;
import Person.*;

class Notification{
    // properties
    LocalDateTime date;
    String text;
    Person sender;
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