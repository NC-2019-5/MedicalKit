package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemByMIIdRequest;
import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemRequest;
import com.netcracker.group5.medkit.model.dto.purchase.EditPurchaseItemRequest;
import com.netcracker.group5.medkit.service.MedicineService;
import com.netcracker.group5.medkit.service.NotificationService;
import com.netcracker.group5.medkit.service.PurchaseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@CrossOrigin
@RestController
@Api(value = "purchases")
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    @ApiOperation(value = "FindPurchaseItems")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findPurchaseItems(@PageableDefault Pageable pageable, @RequestParam(required = false) String searchQuery) {
        List<PurchaseItem> purchaseItems = purchaseService.findPurchaseItems(pageable, searchQuery);

        return ResponseEntity.ok(purchaseItems);
    }

    @PostMapping
    @ApiOperation(value = "AddPurchaseItem")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> addPurchaseItem(@Valid
                                             @RequestBody AddPurchaseItemRequest addPurchaseItemRequest) {
        PurchaseItem purchaseItem = new PurchaseItem(addPurchaseItemRequest);
        purchaseService.savePurchaseItem(purchaseItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping
    @ApiOperation(value = "EditPurchaseItem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> editPurchaseItem(@Valid
                                              @RequestBody EditPurchaseItemRequest request) {
        PurchaseItem purchaseItem = new PurchaseItem(request);
        PurchaseItem savedPurchaseItem = purchaseService.savePurchaseItem(purchaseItem);

        return ResponseEntity.ok(savedPurchaseItem);
    }

    @DeleteMapping
    @ApiOperation(value = "BulkDeletePurchaseItems")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> bulkDeletePurchaseItems(@NotEmpty(message = "Input id list of purchase items can not be empty")
                                                     @RequestParam("id") List<Long> idList) {
        purchaseService.bulkDeletePurchaseItems(idList);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/medicine-instance")
    @ApiOperation(value = "AddPurchaseItemByMedicineInstanceId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> addPurchaseItemByMedicineInstanceId(@Valid
                                                                 @RequestBody AddPurchaseItemByMIIdRequest addPurchaseItemByMIRequest) {

        PurchaseItem purchaseItem = PurchaseItem.newBuilder()
                .setMedicine(Medicine.newBuilder()
                        .setId(medicineService.findMedicineIdByMedicineInstanceId(addPurchaseItemByMIRequest.getMedicineInstanceId()))
                        .build())
                .setAmount(addPurchaseItemByMIRequest.getAmount())
                .build();
        purchaseService.savePurchaseItem(purchaseItem);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
