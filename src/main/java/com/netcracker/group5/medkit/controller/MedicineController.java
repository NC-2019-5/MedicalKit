package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.dto.medicine.FindMedicinesResponseItem;
import com.netcracker.group5.medkit.model.dto.medicine.MedicineInstanceRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.MedicineRequestItem;
import com.netcracker.group5.medkit.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/all-medicines")
    public ResponseEntity<?> findAllMedicines(@PageableDefault Pageable pageable) {
        List<Medicine> medicines = medicineService.findAllMedicines(pageable);
        return ResponseEntity.ok(new FindMedicinesResponseItem(medicines));
    }

    @DeleteMapping("/all-medicines")
    public ResponseEntity<?> deleteMedicine(@RequestParam Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/all-medicines/add")
    public ResponseEntity<?> createMedicine(@RequestBody MedicineRequestItem medicineRequestItem) {
        Medicine medicine = medicineService.saveMedicine(new Medicine(medicineRequestItem));
        return ResponseEntity.ok(medicine);
    }
}
