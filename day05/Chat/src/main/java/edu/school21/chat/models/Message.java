package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private final Long          id;
    private final User          author;
    private final Chatroom      room;
    private final String        text;
    private final LocalDateTime dateTime;

    Message(Long id, User author, Chatroom room, String text,
            LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public int  hashCode() {
        return Objects.hash(id, author, text, room, dateTime);
    }

    @Override
    public boolean  equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message comp = (Message) obj;
        return id.equals(comp.id)
                & text.equals(comp.text)
                & room.equals(comp.room)
                & text.equals(comp.text)
                & dateTime.equals(comp.dateTime);
    }

    @Override
    public String   toString() {
        return "Chatroom{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", room=" + room +
                ", text=" + text +
                ", date and time=" + dateTime +
                '}';
    }
}
