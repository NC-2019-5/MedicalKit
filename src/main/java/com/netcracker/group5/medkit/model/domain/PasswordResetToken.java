package com.netcracker.group5.medkit.model.domain;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;

import java.util.Date;
import java.util.Objects;

public class PasswordResetToken {

    private String userEmail;
    private String token;
    private Date cratedDate;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCratedDate() {
        return cratedDate;
    }

    public void setCratedDate(Date cratedDate) {
        this.cratedDate = cratedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordResetToken that = (PasswordResetToken) o;
        return userEmail.equals(that.userEmail) &&
                token.equals(that.token) &&
                cratedDate.equals(that.cratedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, token, cratedDate);
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "userEmail='" + userEmail + '\'' +
                ", token='" + token + '\'' +
                ", cratedDate=" + cratedDate +
                '}';
    }

    public static PasswordResetToken.Builder newBuilder() {
        return new PasswordResetToken().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public PasswordResetToken.Builder setUserEmail(String userEmail) {
            PasswordResetToken.this.userEmail = userEmail;
            return this;
        }

        public PasswordResetToken.Builder setToken(String token){
            PasswordResetToken.this.token = token;
            return this;
        }

        public PasswordResetToken.Builder setCreatedDate(Date createdDate){
            PasswordResetToken.this.cratedDate = createdDate;
            return this;
        }

        public PasswordResetToken build(){return PasswordResetToken.this;}
    }

}
