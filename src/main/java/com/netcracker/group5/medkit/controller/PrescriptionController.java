package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionItemRequest;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionRequest;
import com.netcracker.group5.medkit.model.dto.prescription.FindPrescriptionItemsResponse;
import com.netcracker.group5.medkit.model.dto.prescription.FindPrescriptionsResponse;
import com.netcracker.group5.medkit.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Controller
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/profile/prescriptions")
    public ResponseEntity<?> findPrescriptionsByPatientId(@PageableDefault Pageable pageable){
        List<Prescription> prescriptions = prescriptionService.findPrescriptionsByPatientId(pageable);
        FindPrescriptionsResponse prescriptionsResponse = new FindPrescriptionsResponse(prescriptions);

        return ResponseEntity.ok(prescriptionsResponse);
    }

    @PostMapping("/profile/prescriptions/add")
    public ResponseEntity<?> addPrescription(@RequestBody AddPrescriptionRequest addPrescriptionRequest) {
        Prescription prescription = new Prescription(addPrescriptionRequest);
        prescriptionService.addPrescription(prescription);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile/prescriptions/delete")
    public ResponseEntity<?> deletePrescription(@Valid @RequestParam Long id) {
        prescriptionService.deletePrescriptionWithItems(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile/prescription-items")
    public ResponseEntity<?> findPrescriptionItemsByPrescriptionId(@PageableDefault Pageable pageable, @RequestParam("id") Long prescriptionId){
        List<PrescriptionItem> prescriptionItems = prescriptionService.findPrescriptionItemsByPrescriptionId(pageable, prescriptionId);
        FindPrescriptionItemsResponse prescriptionItemsResponse = new FindPrescriptionItemsResponse(prescriptionItems);

        return ResponseEntity.ok(prescriptionItemsResponse);
    }

    @PostMapping("/profile/prescription-items/add")
    public ResponseEntity<?> addPrescriptionItem(@RequestBody AddPrescriptionItemRequest addPrescriptionItemRequest) {
        PrescriptionItem prescriptionItem = new PrescriptionItem(addPrescriptionItemRequest);
        prescriptionService.addPrescriptionItem(prescriptionItem);

        return ResponseEntity.ok().build();
    }

}
