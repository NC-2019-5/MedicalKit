package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.enumeration.UserRole;
import com.netcracker.group5.medkit.model.domain.request.Request;
import java.util.List;

public class Administrator extends User {
    public Administrator(long id, String email, String password, UserRole userRole, List<Request> notifications) {
        super(id, email, password, userRole, notifications);
    }
}
