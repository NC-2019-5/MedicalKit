package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.dto.medicine.MedicineInstanceResponseItem;
import com.netcracker.group5.medkit.service.MedicineInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class MedicineInstanceController {

    @Autowired
    private MedicineInstanceService medicineInstanceService;

    @GetMapping("/profile/medicine-kit")
    public ResponseEntity<?> findMedicineInstances(@PageableDefault Pageable pageable, String searchQuery){
        MedicineInstanceResponseItem responseItem = new MedicineInstanceResponseItem(medicineInstanceService.findMedicineInstances(pageable, searchQuery));
        return ResponseEntity.ok(responseItem);
    }
}
