package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MedicineInstance implements Requestable {

    private long id;
    private Medicine medicine;
    private Date selfLife;
    private int amount;
}
