package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private final Long              userID;
    private final String            login;
    private String                  password;
    private final List<Chatroom>    rooms;
    private final List<Chatroom>    userRooms;

    public User(Long userID, String login, String password,
                List<Chatroom> rooms, List<Chatroom> userRooms) {
        this.userID = userID;
        this.login = login;
        this.password = password;
        this.rooms = rooms;
        this.userRooms = userRooms;
    }

    public String   getLogin() {
        return login;
    }

    public Long getUserID() {
        return userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int  hashCode() {
        return Objects.hash(userID, login, password, rooms, userRooms);
    }

    @Override
    public boolean  equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User comp = (User) obj;
        return userID.equals(comp.userID)
                & login.equals(comp.login)
                & password.equals(comp.password)
                & rooms == comp.rooms
                & userRooms == comp.userRooms;
    }

    @Override
    public String   toString() {
        return "Chatroom{" +
                "id=" + userID +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", rooms=" + rooms +
                ", user rooms=" + userRooms +
                '}';
    }
}
