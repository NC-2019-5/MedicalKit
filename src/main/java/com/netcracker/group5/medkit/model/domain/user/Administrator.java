package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.request.Request;

import java.util.List;

public class Administrator extends User {
    public Administrator(Long id, String email, String password, Role role, List<Request> notifications) {
        super(id, email, password, role, notifications);
    }
}
