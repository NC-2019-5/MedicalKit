package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.Requestable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PrescriptionItem implements Requestable {

    private long id;
    private Medicine medicine;
    private Date startDate;
    private Date endDate;
    private int takingDurationDays;
    private Date takingTime;
    private String description;
}
