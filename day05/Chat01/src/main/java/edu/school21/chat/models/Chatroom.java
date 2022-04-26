package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private final Long          chatroomID;
    private String              chatroomName;
    private String              chatroomOwner;
    private final List<Message> messages;

    public Chatroom(Long id, String name, String owner, List<Message> messages) {
        chatroomID = id;
        chatroomName = name;
        chatroomOwner = owner;
        this.messages = messages;
    }

    public Long getChatroomID() {
        return chatroomID;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public String getChatroomOwner() {
        return chatroomOwner;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public void setChatroomOwner(String chatroomOwner) {
        this.chatroomOwner = chatroomOwner;
    }

    @Override
    public int  hashCode() {
        return Objects.hash(chatroomID, chatroomName, chatroomOwner, messages);
    }

    @Override
    public boolean  equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Chatroom)) {
            return false;
        }
        Chatroom comp = (Chatroom) obj;
        return chatroomID.equals(comp.chatroomID)
                & chatroomName.equals(comp.chatroomName)
                & chatroomOwner.equals(comp.chatroomOwner)
                & messages == comp.messages;
    }

    @Override
    public String   toString() {
        return "Chatroom{" +
                "id=" + chatroomID +
                ", name='" + chatroomName + '\'' +
                ", owner=" + chatroomOwner +
                ", roomMessages=" + messages +
                '}';
    }
}
