package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.dto.medicine.EditMedicineRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.SaveMedicineRequestItem;
import com.netcracker.group5.medkit.service.MedicineService;
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
@Api(value = "all-medicines")
@RequestMapping("/all-medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    @ApiOperation(value = "FindAllMedicines")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "Authorization", value = "Bearer token",
                    required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    })
    public ResponseEntity<?> findAllMedicines(@PageableDefault Pageable pageable,
                                              @Size(max = 256, message = "Search query is too long")
                                              @RequestParam(name = "query", required = false) String searchQuery) {
        List<Medicine> medicines = medicineService.findAllMedicines(pageable, searchQuery);

        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/search-by-params")
    @ApiOperation(value = "FindAllMedicinesByParams")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "Authorization", value = "Bearer token",
                    required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    })
    public ResponseEntity<?> findAllMedicinesByParams(@PageableDefault Pageable pageable,
                                                      @Size(max = 256, message = "Search query is too long")
                                                      @RequestParam(name = "query", required = false) String searchQuery) {
        List<Medicine> medicines = medicineService.findAllMedicinesByParams(pageable, searchQuery);

        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "FindMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No data found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findMedicine(@NotNull(message = "Id cannot be empty")
                                          @Positive(message = "Id must be greater than 0")
                                          @PathVariable Long id) {
        Medicine medicine = medicineService.findMedicine(id);

        if (medicine != null){
            return ResponseEntity.ok(medicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "DeleteMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No data found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> deleteMedicine(@NotNull(message = "Id cannot be empty")
                                            @Positive(message = "Id must be greater than 0")
                                            @RequestParam Long id) {
        try {
            medicineService.deleteMedicine(id);

            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "SaveMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> saveMedicine(@Valid
                                          @RequestBody SaveMedicineRequestItem requestItem) {
        Medicine medicine = medicineService.saveMedicine(new Medicine(requestItem));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicine);
    }

    @PutMapping
    @ApiOperation(value = "EditMedicine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> editMedicine(@Valid
                                          @RequestBody EditMedicineRequestItem requestItem) {
        Medicine medicine = medicineService.saveMedicine(new Medicine(requestItem));

        return ResponseEntity.ok(medicine);
    }
}
