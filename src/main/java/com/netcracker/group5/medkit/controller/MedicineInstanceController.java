package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.dto.medicine.EditMedicineInstanceRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.GetMedicineInstanceResponseItem;
import com.netcracker.group5.medkit.model.dto.medicine.SaveMedicineInstanceRequestItem;
import com.netcracker.group5.medkit.service.MedicineInstanceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/medicine-kit")
@Api(value = "medicine-kit")
public class MedicineInstanceController {

    @Autowired
    private MedicineInstanceService medicineInstanceService;

    @GetMapping
    @ApiOperation(value = "FindAllMedicines")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findMedicineInstances(@PageableDefault Pageable pageable,
                                                   @Size(max = 256, message = "Search query is too long")
                                                   @RequestParam(name = "query", required = false) String searchQuery) {
        List<MedicineInstance> medicineInstances = medicineInstanceService.findMedicineInstances(pageable, searchQuery);

        return ResponseEntity.ok(medicineInstances);
    }

    @PostMapping
    @ApiOperation(value = "SaveMedicineInstance")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> saveMedicineInstance(@Valid
                                                  @RequestBody SaveMedicineInstanceRequestItem requestItem) {
        MedicineInstance medicineInstance = medicineInstanceService.saveMedicineInstance(new MedicineInstance(requestItem));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicineInstance);
    }

    @PutMapping
    @ApiOperation(value = "EditMedicineInstance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> editMedicineInstance(@Valid
                                                  @RequestBody EditMedicineInstanceRequestItem requestItem) {
        MedicineInstance savedMedicineInstance = medicineInstanceService.saveMedicineInstance(new MedicineInstance(requestItem));

        return ResponseEntity.ok(savedMedicineInstance);
    }

    @DeleteMapping
    @ApiOperation(value = "DeleteMedicineInstance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> deleteMedicineInstance(@NotNull(message = "Id cannot be empty")
                                                    @Positive(message = "Id must be greater than 0")
                                                    @RequestParam Long id) {
        try {
            medicineInstanceService.deleteMedicineInstance(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
