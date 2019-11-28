package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.Location;
import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.Speciality;
import com.netcracker.group5.medkit.model.domain.enumeration.UserRole;
import com.netcracker.group5.medkit.model.domain.request.Request;

import java.util.Date;
import java.util.List;

public class Doctor extends User implements Requestable {

    private String fullName;
    private Speciality speciality;
    private Date dateOfBirth;
    private String workExperience;
    private Location placeOfWork;
    private String phoneNumber;
    private List<Patient> patients;

    public Doctor(long id, String email, String password, UserRole userRole, List<Request> notifications, String fullName, Speciality speciality, Date dateOfBirth, String workExperience, Location placeOfWork, String phoneNumber, List<Patient> patients) {
        super(id, email, password, userRole, notifications);
        this.fullName = fullName;
        this.speciality = speciality;
        this.dateOfBirth = dateOfBirth;
        this.workExperience = workExperience;
        this.placeOfWork = placeOfWork;
        this.phoneNumber = phoneNumber;
        this.patients = patients;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public Location getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(Location placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
