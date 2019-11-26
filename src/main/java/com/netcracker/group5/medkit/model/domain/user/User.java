package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.request.Request;
import com.netcracker.group5.medkit.model.domain.enumeration.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class User {

    protected long id;
    protected String email;
    protected String password;
    protected UserRole userRole;
    protected List<Request> notifications;


}
