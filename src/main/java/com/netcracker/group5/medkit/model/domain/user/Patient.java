package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.purchase.Purchase;
import com.netcracker.group5.medkit.model.dto.user.EditPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientRequestItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Patient extends User {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Sex sex;
    private float weight;
    private float height;
    private String location;
    private String phoneNumber;
    private List<Doctor> attendingDoctors;
    private List<Purchase> purchases;
    private List<MedicineInstance> medicineInstances;
    private List<Prescription> prescriptions;

    public Patient(RegisterPatientRequestItem requestItem) {
        this.email = requestItem.getEmail();
        this.password = requestItem.getPassword();
        this.name = requestItem.getName();
        this.surname = requestItem.getSurname();
        this.birthDate = requestItem.getBirthDate();
        this.location = requestItem.getLocation();
        this.weight = requestItem.getWeight();
        this.height = requestItem.getHeight();
        this.phoneNumber = requestItem.getPhoneNumber();
        this.sex = requestItem.getSex();
        this.role = Role.PATIENT;
    }

    public Patient(EditPatientRequestItem requestItem) {
        this.id = requestItem.getId();
        this.email = requestItem.getEmail();
        this.name = requestItem.getName();
        this.surname = requestItem.getSurname();
        this.birthDate = requestItem.getBirthDate();
        this.location = requestItem.getLocation();
        this.weight = requestItem.getWeight();
        this.height = requestItem.getHeight();
        this.phoneNumber = requestItem.getPhoneNumber();
        this.sex = requestItem.getSex();
        this.role = Role.PATIENT;
    }

    private Patient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Float.compare(patient.weight, weight) == 0 &&
                Float.compare(patient.height, height) == 0 &&
                Objects.equals(name, patient.name) &&
                Objects.equals(surname, patient.surname) &&
                Objects.equals(birthDate, patient.birthDate) &&
                sex == patient.sex &&
                Objects.equals(location, patient.location) &&
                Objects.equals(phoneNumber, patient.phoneNumber) &&
                Objects.equals(attendingDoctors, patient.attendingDoctors) &&
                Objects.equals(purchases, patient.purchases) &&
                Objects.equals(medicineInstances, patient.medicineInstances) &&
                Objects.equals(prescriptions, patient.prescriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, birthDate, sex, weight, height, location, phoneNumber, attendingDoctors, purchases, medicineInstances, prescriptions);
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", weight=" + weight +
                ", height=" + height +
                ", location=" + location +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", attendingDoctors=" + attendingDoctors +
                ", purchases=" + purchases +
                ", medicineInstances=" + medicineInstances +
                ", prescriptions=" + prescriptions +
                '}';
    }

    public static Builder newBuilder() {
        return new Patient().new Builder();
    }

    public class Builder extends User.AbstractBuilder<Builder> {

        private Builder() {
        }

        public Builder setName(String name) {
            Patient.this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            Patient.this.surname = surname;
            return this;
        }

        public Builder setBirthDate(LocalDate birthDate) {
            Patient.this.birthDate = birthDate;
            return this;
        }

        public Builder setSex(Sex sex) {
            Patient.this.sex = sex;
            return this;
        }

        public Builder setWeight(float weight) {
            Patient.this.weight = weight;
            return this;
        }

        public Builder setHeight(float height) {
            Patient.this.height = height;
            return this;
        }

        public Builder setLocation(String location) {
            Patient.this.location = location;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            Patient.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAttendingDoctors(List<Doctor> attendingDoctors) {
            Patient.this.attendingDoctors = attendingDoctors;
            return this;
        }

        public Builder setPurchases(List<Purchase> purchases) {
            Patient.this.purchases = purchases;
            return this;
        }

        public Builder setMedicineInstances(List<MedicineInstance> medicineInstances) {
            Patient.this.medicineInstances = medicineInstances;
            return this;
        }

        public Builder setPrescriptions(List<Prescription> prescriptions) {
            Patient.this.prescriptions = prescriptions;
            return this;
        }

        public Patient build() {
            return Patient.this;
        }
    }
}
