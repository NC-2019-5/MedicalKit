package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemRequest;
import com.netcracker.group5.medkit.model.dto.purchase.FindPurchaseItemsResponse;
import com.netcracker.group5.medkit.service.PurchaseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@CrossOrigin
@RestController
@Api(value = "purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/profile/purchases")
    @ApiOperation(value = "FindPurchaseItems")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findPurchaseItems(@PageableDefault Pageable pageable, @RequestParam(required = false) String searchQuery) {
        List<PurchaseItem> purchaseItems = purchaseService.findPurchaseItems(pageable, searchQuery);
        FindPurchaseItemsResponse responseItem = new FindPurchaseItemsResponse(purchaseItems);

        return ResponseEntity.ok(responseItem);
    }

    @PostMapping("/profile/purchases")
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

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile/purchases")
    @ApiOperation(value = "DeletePurchaseItem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> deletePurchaseItem(@NotNull(message = "Id can not be empty")
                                                @Positive(message = "Id must be greater than 0")
                                                @RequestParam Long id) {
        purchaseService.deletePurchaseItem(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile/purchases/bulk-delete")
    @ApiOperation(value = "BulkDeletePurchaseItems")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> bulkDeletePurchaseItems(@RequestParam("id")
                                                     @NotEmpty(message = "Input id list of purchase items can not be empty")
                                                                 List<Long> idList) {
        purchaseService.bulkDeletePurchaseItems(idList);

        return ResponseEntity.ok().build();
    }
}
