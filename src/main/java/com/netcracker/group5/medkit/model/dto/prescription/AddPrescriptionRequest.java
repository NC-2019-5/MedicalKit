package com.netcracker.group5.medkit.model.dto.prescription;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class AddPrescriptionRequest {

    @NotBlank(message = "Prescription name can not be empty")
    private String name;

    @NotNull(message = "Prescription date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotNull(message = "Doctor id is mandatory")
    @Positive(message = "Doctor id must be greater than 0")
    private Long doctorId;

    public AddPrescriptionRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
