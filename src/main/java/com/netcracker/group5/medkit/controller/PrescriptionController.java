package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionItemRequest;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionRequest;
import com.netcracker.group5.medkit.model.dto.prescription.FindPrescriptionItemsResponse;
import com.netcracker.group5.medkit.model.dto.prescription.FindPrescriptionsResponse;
import com.netcracker.group5.medkit.service.PrescriptionService;
import io.swagger.annotations.*;
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
@Api(value = "prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/profile/prescriptions")
    @ApiOperation(value = "FindPrescriptionsByPatientId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findPrescriptionsByPatientId(@PageableDefault Pageable pageable) {
        List<Prescription> prescriptions = prescriptionService.findPrescriptionsByPatientId(pageable);
        FindPrescriptionsResponse prescriptionsResponse = new FindPrescriptionsResponse(prescriptions);

        return ResponseEntity.ok(prescriptionsResponse);
    }

    @PostMapping("/profile/prescriptions/add")
    @ApiOperation(value = "AddPrescription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> addPrescription(@Valid
                                             @RequestBody AddPrescriptionRequest addPrescriptionRequest) {
        Prescription prescription = new Prescription(addPrescriptionRequest);
        prescriptionService.addPrescription(prescription);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile/prescriptions/delete")
    @ApiOperation(value = "DeletePrescription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> deletePrescription(@NotNull(message = "Id can not be empty")
                                                @Positive(message = "Id must be greater than 0")
                                                @RequestParam Long id) {
        prescriptionService.deletePrescriptionWithItems(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile/prescription-items")
    @ApiOperation(value = "FindPrescriptionItemsByPrescriptionId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findPrescriptionItemsByPrescriptionId(@PageableDefault Pageable pageable, @RequestParam("id") Long prescriptionId) {
        List<PrescriptionItem> prescriptionItems = prescriptionService.findPrescriptionItemsByPrescriptionId(pageable, prescriptionId);
        FindPrescriptionItemsResponse prescriptionItemsResponse = new FindPrescriptionItemsResponse(prescriptionItems);

        return ResponseEntity.ok(prescriptionItemsResponse);
    }

    @PostMapping("/profile/prescription-items/add")
    @ApiOperation(value = "AddPrescriptionItem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> addPrescriptionItem(@Valid
                                                 @RequestBody AddPrescriptionItemRequest addPrescriptionItemRequest) {
        PrescriptionItem prescriptionItem = new PrescriptionItem(addPrescriptionItemRequest);
        prescriptionService.addPrescriptionItem(prescriptionItem);

        return ResponseEntity.ok().build();
    }

}
