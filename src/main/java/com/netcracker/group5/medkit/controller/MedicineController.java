package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/all-medicines")
    public void findMedicines(@PageableDefault Pageable pageable) {
        medicineService.findMedicines(pageable);
    }
}
