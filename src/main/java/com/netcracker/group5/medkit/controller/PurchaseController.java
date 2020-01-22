package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemRequest;
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
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

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
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> addPurchaseItem(@Valid
                                             @RequestBody AddPurchaseItemRequest addPurchaseItemRequest) {
        PurchaseItem purchaseItem = new PurchaseItem(addPurchaseItemRequest);
        purchaseService.addPurchaseItem(purchaseItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
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
}
