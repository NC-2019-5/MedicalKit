package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.dto.medicine.EditMedicineInstanceRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.GetMedicineInstanceResponseItem;
import com.netcracker.group5.medkit.model.dto.medicine.MedicineInstanceResponseItem;
import com.netcracker.group5.medkit.model.dto.medicine.SaveMedicineInstanceRequestItem;
import com.netcracker.group5.medkit.service.MedicineInstanceService;
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
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@CrossOrigin
@RestController
public class MedicineInstanceController {

    @Autowired
    private MedicineInstanceService medicineInstanceService;

    @GetMapping("/medicine-kit")
    public ResponseEntity<?> findMedicineInstances(@PageableDefault Pageable pageable,
                                                   @Size(max = 256, message = "Search query is too long")
                                                   @RequestParam(required = false) String searchQuery) {
        List<MedicineInstance> medicineInstances = medicineInstanceService.findMedicineInstances(pageable, searchQuery);
        MedicineInstanceResponseItem responseItem = new MedicineInstanceResponseItem(medicineInstances);

        return ResponseEntity.ok(responseItem);
    }

    @PostMapping("/medicine-kit")
    public ResponseEntity<?> saveMedicineInstance(@Valid
                                                  @RequestBody SaveMedicineInstanceRequestItem requestItem) {
        MedicineInstance medicineInstance = medicineInstanceService.saveMedicineInstance(new MedicineInstance(requestItem));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicineInstance);
    }

    @PutMapping("/medicine-kit")
    public ResponseEntity<?> editMedicineInstance(@Valid
                                                  @RequestBody EditMedicineInstanceRequestItem requestItem) {
        MedicineInstance savedMedicineInstance = medicineInstanceService.saveMedicineInstance(new MedicineInstance(requestItem));
        GetMedicineInstanceResponseItem responseItem = new GetMedicineInstanceResponseItem(savedMedicineInstance);

        return ResponseEntity.ok(responseItem);
    }

    @DeleteMapping("/medicine-kit")
    public ResponseEntity<?> deleteMedicineInstance(@NotNull(message = "Id cannot be empty")
                                                    @Positive(message = "Id must be greater than 0")
                                                    @RequestParam Long id) {
        medicineInstanceService.deleteMedicineInstance(id);

        return ResponseEntity.ok().build();
    }
}
