package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.RequestType;
import com.netcracker.group5.medkit.model.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request<T extends Requestable> {

    protected long id;
    protected Date timeWhenDelivered;
    protected RequestType type;
    protected User sender;
    protected User recipient;
    protected T body;
    protected String template;
}
