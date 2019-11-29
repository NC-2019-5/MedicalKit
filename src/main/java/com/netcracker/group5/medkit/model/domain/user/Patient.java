package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.Location;
import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.Sex;
import com.netcracker.group5.medkit.model.domain.enumeration.UserRole;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.purchase.Purchase;
import com.netcracker.group5.medkit.model.domain.request.Request;

import java.util.Date;
import java.util.List;

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

    public Patient(Long id, String email, String password, UserRole userRole, List<Request> notifications, String fullName, Date dateOfBirth, Sex sex, float weight, float height, Location location, String phoneNumber, List<Doctor> attendingDoctors, List<Purchase> purchases, List<MedicineInstance> medicineInstances, List<Prescription> prescriptions) {
        super(id, email, password, userRole, notifications);
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.attendingDoctors = attendingDoctors;
        this.purchases = purchases;
        this.medicineInstances = medicineInstances;
        this.prescriptions = prescriptions;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Doctor> getAttendingDoctors() {
        return attendingDoctors;
    }

    public void setAttendingDoctors(List<Doctor> attendingDoctors) {
        this.attendingDoctors = attendingDoctors;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<MedicineInstance> getMedicineInstances() {
        return medicineInstances;
    }

    public void setMedicineInstances(List<MedicineInstance> medicineInstances) {
        this.medicineInstances = medicineInstances;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
