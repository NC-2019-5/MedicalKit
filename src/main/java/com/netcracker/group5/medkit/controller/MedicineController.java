package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = "/all-medicines",
            produces = "application/json",
            method = RequestMethod.GET)
    public void findMedicines(@RequestParam int page,
                              @RequestParam int size,
                              @RequestParam String orderBy,
                              @RequestParam String direction,
                              @RequestParam String searchQuery) {
        medicineService.findMedicines(0, 0, "", "", "");
    }
}
