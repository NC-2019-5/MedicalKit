package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.request.Request;

import java.util.List;
import java.util.Objects;

public class User implements Requestable {

    protected Long id;
    protected String email;
    protected String password;
    protected Role role;
    protected List<Request<?>> notifications;

    protected User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Request<?>> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Request<?>> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(notifications, user.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role, notifications);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", notifications=" + notifications;
    }

    public static Builder newUserBuilder() {
        return new User().new Builder();
    }

    public class Builder extends AbstractBuilder<Builder> {
        private Builder() {
        }

        public User build() {
            return User.this;
        }
    }

    public abstract class AbstractBuilder<B extends AbstractBuilder<B>> {

        protected AbstractBuilder() {
        }

        public B setId(Long id) {
            User.this.id = id;
            return getThis();
        }

        public B setEmail(String email) {
            User.this.email = email;
            return getThis();
        }

        public B setPassword(String password) {
            User.this.password = password;
            return getThis();
        }

        public B setRole(Role role) {
            User.this.role = role;
            return getThis();
        }

        public B setNotifications(List<Request<?>> notifications) {
            User.this.notifications = notifications;
            return getThis();
        }

        private B getThis() {
            return (B) this;
        }
    }
}
