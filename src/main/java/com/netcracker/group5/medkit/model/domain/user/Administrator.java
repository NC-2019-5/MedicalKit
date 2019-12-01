package com.netcracker.group5.medkit.model.domain.user;

public class Administrator extends User {

    private Administrator() {
    }

    @Override
    public String toString() {
        return "Administrator{" +
                super.toString() +
                '}';
    }

    public static Builder newBuilder() {
        return new Administrator().new Builder();
    }

    public class Builder extends User.Builder<Builder> {

        private Builder() {
        }

        public Administrator build() {
            return Administrator.this;
        }
    }
}
