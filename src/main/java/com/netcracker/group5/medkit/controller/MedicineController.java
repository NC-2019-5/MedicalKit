package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.dto.medicine.FindMedicinesResponseItem;
import com.netcracker.group5.medkit.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/all-medicines")
    public ResponseEntity<?> findMedicines(@PageableDefault Pageable pageable) {
        List<Medicine> medicines = medicineService.findMedicines(pageable);
        return ResponseEntity.ok(new FindMedicinesResponseItem(medicines));
    }
}
