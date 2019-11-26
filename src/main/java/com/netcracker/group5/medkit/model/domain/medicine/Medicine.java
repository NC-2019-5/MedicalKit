package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.TakingMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Medicine implements Requestable {

    private long id;
    private String name;
    private String manufacturer;
    private String formOfProduction;
    private String contraindications;
    private String interactions;
    private String packageContent;
    private TakingMethod takingMethod;
    private String description;
}
