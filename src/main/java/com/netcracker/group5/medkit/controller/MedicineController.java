package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.dto.medicine.EditMedicineRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.FindMedicinesResponseItem;
import com.netcracker.group5.medkit.model.dto.medicine.SaveMedicineRequestItem;
import com.netcracker.group5.medkit.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
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

    @GetMapping("/all-medicines/{id}")
    public ResponseEntity<?> findMedicine(@NotNull(message = "Id cannot be empty")
                                          @Positive(message = "Id must be greater than 0")
                                          @PathVariable Long id) {
        Medicine medicine = medicineService.findMedicine(id);

        return ResponseEntity.ok(medicine);
    }

    @DeleteMapping("/all-medicines")
    public ResponseEntity<?> deleteMedicine(@NotNull(message = "Id cannot be empty")
                                            @Positive(message = "Id must be greater than 0")
                                            @RequestParam Long id) {
        medicineService.deleteMedicine(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/all-medicines")
    public ResponseEntity<?> saveMedicine(@Valid
                                          @RequestBody SaveMedicineRequestItem requestItem) {
        Medicine medicine = medicineService.saveMedicine(new Medicine(requestItem));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicine);
    }

    @PutMapping("/all-medicines")
    public ResponseEntity<?> editMedicine(@Valid
                                          @RequestBody EditMedicineRequestItem requestItem) {
        Medicine medicine = medicineService.saveMedicine(new Medicine(requestItem));

        return ResponseEntity.ok(medicine);
    }
}
