package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.request.EditEntityRequest;
import com.netcracker.group5.medkit.model.domain.request.Reminder;
import com.netcracker.group5.medkit.model.domain.request.Request;
import com.netcracker.group5.medkit.model.domain.user.Administrator;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping("/")
    public String hello() {
        Medicine medicine = Medicine.newBuilder()
                .setId(121L)
                .setName("Medicine 1")
                .build();

        User patient = Patient.newBuilder()
                .setId(1L)
                .setName("John")
                .setSurname("Smith")
                .setHeight(175.5f)
                .build();

        User admin = Administrator.newBuilder()
                .setId(1L)
                .setEmail("admin@amdin.com")
                .setPassword("qwe123")
                .build();

        User doctor = Doctor.newBuilder()
                .setId(12313L)
                .setName("Antony")
                .setSurname("Wallace")
                .setPhoneNumber("012-123-12-12")
                .setEmail("aaa@gmail.com")
                .setWorkExperience("10 years")
                .build();

        Reminder<Medicine> reminder = Reminder.<Medicine>newBuilder()
                .setId(1L)
                .build();

        Request<Medicine> request = Request.<Medicine>newRequestBuilder()
                .setId(2L)
                .setBody(medicine)
                .setSender(doctor)
                .setRecipient(admin)
                .build();

        EditEntityRequest<Medicine> editEntityRequest = EditEntityRequest.<Medicine>newBuilder()
                .setId(3L)
                .setBody(medicine)
                .setSender(patient)
                .setRecipient(admin)
                .build();

        System.out.println(reminder);
        System.out.println(request);
        System.out.println(editEntityRequest);
        System.out.println(admin);

        return request.toString();
    }
}
