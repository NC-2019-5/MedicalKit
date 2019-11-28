package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.Location;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.Sex;
import com.netcracker.group5.medkit.model.domain.purchase.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Patient extends User implements Requestable {

    private String fullName;
    private Date dateOfBirth;
    private Sex sex;
    private float weight;
    private float height;
    private Location location;
    private String phoneNumber;
    private List<Doctor> attendingDoctors;
    private List<Purchase> purchases;
    private List<MedicineInstance> medicineInstances;
    private List<Prescription> prescriptions;
}
