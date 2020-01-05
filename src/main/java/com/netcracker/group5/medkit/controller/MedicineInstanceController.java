package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.dto.medicine.MedicineInstanceRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.MedicineInstanceResponseItem;
import com.netcracker.group5.medkit.service.MedicineInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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

    @PostMapping("/profile/medicine-kit/add")
    public ResponseEntity<?> createMedicineInstance(@Valid @RequestBody MedicineInstanceRequestItem medicineInstanceRequestItem) {
        MedicineInstance medicineInstance = medicineInstanceService.createMedicineInstance(new MedicineInstance(medicineInstanceRequestItem));
        return ResponseEntity.ok(medicineInstance);
    }
}
