package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Prescription implements Requestable {

    private Long id;
    private String name;
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;

    public Prescription(){

    }

    public Prescription(AddPrescriptionRequest prescriptionRequest) {
        this.name = prescriptionRequest.getName();
        this.date = prescriptionRequest.getDate();
        this.doctor = Doctor.newBuilder()
                .setId(prescriptionRequest.getDoctorId())
                .build();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, doctor, patient, date);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Builder newBuilder() {
        return new Prescription().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            Prescription.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            Prescription.this.name = name;
            return this;
        }

        public Builder setDoctor(Doctor doctor) {
            Prescription.this.doctor = doctor;
            return this;
        }

        public Builder setPatient(Patient patient) {
            Prescription.this.patient = patient;
            return this;
        }

        public Builder setDate(LocalDate date) {
            Prescription.this.date = date;
            return this;
        }

        public Prescription build() {
            return Prescription.this;
        }
    }
}
