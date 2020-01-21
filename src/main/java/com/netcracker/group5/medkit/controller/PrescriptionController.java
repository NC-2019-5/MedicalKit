package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionItemRequest;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionRequest;
import com.netcracker.group5.medkit.model.dto.prescription.FindPrescriptionItemsResponse;
import com.netcracker.group5.medkit.model.dto.prescription.FindPrescriptionsResponse;
import com.netcracker.group5.medkit.service.PrescriptionService;
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
import java.util.List;

@Validated
@CrossOrigin
@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    public ResponseEntity<?> findPrescriptionsByPatientId(@PageableDefault Pageable pageable) {
        List<Prescription> prescriptions = prescriptionService.findPrescriptionsByPatientId(pageable);
        FindPrescriptionsResponse prescriptionsResponse = new FindPrescriptionsResponse(prescriptions);

        return ResponseEntity.ok(prescriptionsResponse);
    }

    @PostMapping("/prescriptions")
    public ResponseEntity<?> addPrescription(@Valid
                                             @RequestBody AddPrescriptionRequest addPrescriptionRequest) {
        Prescription prescription = new Prescription(addPrescriptionRequest);
        prescriptionService.addPrescription(prescription);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/prescriptions")
    public ResponseEntity<?> deletePrescription(@NotNull(message = "Id can not be empty")
                                                @Positive(message = "Id must be greater than 0")
                                                @RequestParam Long id) {
        prescriptionService.deletePrescriptionWithItems(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/prescription-items")
    public ResponseEntity<?> findPrescriptionItemsByPrescriptionId(@PageableDefault Pageable pageable, @RequestParam("id") Long prescriptionId) {
        List<PrescriptionItem> prescriptionItems = prescriptionService.findPrescriptionItemsByPrescriptionId(pageable, prescriptionId);
        FindPrescriptionItemsResponse prescriptionItemsResponse = new FindPrescriptionItemsResponse(prescriptionItems);

        return ResponseEntity.ok(prescriptionItemsResponse);
    }

    @PostMapping("/prescription-items")
    public ResponseEntity<?> addPrescriptionItem(@Valid
                                                 @RequestBody AddPrescriptionItemRequest addPrescriptionItemRequest) {
        PrescriptionItem prescriptionItem = new PrescriptionItem(addPrescriptionItemRequest);
        prescriptionService.addPrescriptionItem(prescriptionItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
