package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Prescription implements Requestable {

    private long id;
    private Doctor doctor;
    private Patient patient;
    private List<PrescriptionItem> prescriptionItems;
    private Date date;
}
