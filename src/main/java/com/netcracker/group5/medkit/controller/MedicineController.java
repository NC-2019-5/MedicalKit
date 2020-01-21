package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.dto.medicine.EditMedicineRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.FindMedicinesResponseItem;
import com.netcracker.group5.medkit.model.dto.medicine.SaveMedicineRequestItem;
import com.netcracker.group5.medkit.service.MedicineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@Api(value = "all-medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/all-medicines")
    @ApiOperation(value = "FindAllMedicines")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    public ResponseEntity<?> findAllMedicines(@PageableDefault Pageable pageable) {
        List<Medicine> medicines = medicineService.findAllMedicines(pageable);

        return ResponseEntity.ok(new FindMedicinesResponseItem(medicines));
    }

    @GetMapping("/all-medicines/{id}")
    @ApiOperation(value = "FindMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    public ResponseEntity<?> findMedicine(@NotNull(message = "Id cannot be empty")
                                          @Positive(message = "Id must be greater than 0")
                                          @PathVariable Long id) {
        Medicine medicine = medicineService.findMedicine(id);

        return ResponseEntity.ok(medicine);
    }

    @DeleteMapping("/all-medicines")
    @ApiOperation(value = "DeleteMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    public ResponseEntity<?> deleteMedicine(@NotNull(message = "Id cannot be empty")
                                            @Positive(message = "Id must be greater than 0")
                                            @RequestParam Long id) {
        medicineService.deleteMedicine(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/all-medicines")
    @ApiOperation(value = "SaveMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    public ResponseEntity<?> saveMedicine(@Valid
                                          @RequestBody SaveMedicineRequestItem requestItem) {
        Medicine medicine = medicineService.saveMedicine(new Medicine(requestItem));

        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/all-medicines")
    @ApiOperation(value = "EditMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    public ResponseEntity<?> editMedicine(@Valid
                                          @RequestBody EditMedicineRequestItem requestItem) {
        Medicine medicine = medicineService.saveMedicine(new Medicine(requestItem));

        return ResponseEntity.ok(medicine);
    }
}
