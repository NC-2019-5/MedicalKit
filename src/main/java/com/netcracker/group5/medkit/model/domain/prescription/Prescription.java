package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.model.domain.user.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Prescription implements Requestable {

    private Long id;
    private Doctor doctor;
    private Patient patient;
    private List<PrescriptionItem> prescriptionItems;
    private LocalDateTime date;

    private Prescription() {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return id.equals(that.id) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(prescriptionItems, that.prescriptionItems) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, patient, prescriptionItems, date);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", prescriptionItems=" + prescriptionItems +
                ", date=" + date +
                '}';
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

        public Builder setDoctor(Doctor doctor) {
            Prescription.this.doctor = doctor;
            return this;
        }

        public Builder setPatient(Patient patient) {
            Prescription.this.patient = patient;
            return this;
        }

        public Builder setPrescriptionItems(List<PrescriptionItem> prescriptionItems) {
            Prescription.this.prescriptionItems = prescriptionItems;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            Prescription.this.date = date;
            return this;
        }

        public Prescription build() {
            return Prescription.this;
        }
    }
}
