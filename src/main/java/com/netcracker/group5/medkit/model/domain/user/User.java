package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.request.Request;
import com.netcracker.group5.medkit.model.domain.enumeration.UserRole;
import java.util.List;

public abstract class User {

    protected long id;
    protected String email;
    protected String password;
    protected UserRole userRole;
    protected List<Request> notifications;

    public User(long id, String email, String password, UserRole userRole, List<Request> notifications) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.notifications = notifications;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Request> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Request> notifications) {
        this.notifications = notifications;
    }
}
