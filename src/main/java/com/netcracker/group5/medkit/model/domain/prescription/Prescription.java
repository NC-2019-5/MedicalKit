package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.model.domain.user.Patient;

import java.util.Date;
import java.util.List;

public class Prescription implements Requestable {

    private Long id;
    private Doctor doctor;
    private Patient patient;
    private List<PrescriptionItem> prescriptionItems;
    private Date date;

    public Prescription(Long id, Doctor doctor, Patient patient, List<PrescriptionItem> prescriptionItems, Date date) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.prescriptionItems = prescriptionItems;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<PrescriptionItem> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(List<PrescriptionItem> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
