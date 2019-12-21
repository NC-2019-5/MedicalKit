package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = "/all-medicines",
            produces = "application/json",
            method = RequestMethod.GET)
    public void findMedicines(@PageableDefault Pageable pageable) {
        medicineService.findMedicines(pageable);
    }
}
