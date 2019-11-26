package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class EditEntityRequest<T extends Requestable> extends Request<T> {

    private Map<String, String> fields;
}
