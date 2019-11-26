package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.Location;
import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.Speciality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Doctor extends User implements Requestable {

    private String fullName;
    private Speciality speciality;
    private Date dateOfBirth;
    private String workExperience;
    private Location placeOfWork;
    private String phoneNumber;
    private List<Patient> patients;
}
