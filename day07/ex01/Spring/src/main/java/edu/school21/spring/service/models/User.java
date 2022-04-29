package edu.school21.spring.service.models;

import java.util.Objects;

public class User {

    private Long      id;
    private String    email;

    public User() {}

    public User(Long id, String email) {
        this.email = email;
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int  hashCode() {
        return Objects.hash(id, email);
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
        return id.equals(comp.id)
                & email.equals(comp.email);
    }

    @Override
    public String   toString() {
        return "Chatroom{" +
                "id=" + id +
                ", login='" + email +
                '}';
    }
}
