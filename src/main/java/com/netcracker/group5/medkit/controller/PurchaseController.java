package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemRequest;
import com.netcracker.group5.medkit.model.dto.purchase.FindPurchaseItemsResponse;
import com.netcracker.group5.medkit.service.PurchaseService;
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
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<?> findPurchaseItems(@PageableDefault Pageable pageable, @RequestParam(required = false) String searchQuery) {
        List<PurchaseItem> purchaseItems = purchaseService.findPurchaseItems(pageable, searchQuery);
        FindPurchaseItemsResponse responseItem = new FindPurchaseItemsResponse(purchaseItems);

        return ResponseEntity.ok(responseItem);
    }

    @PostMapping
    public ResponseEntity<?> addPurchaseItem(@Valid
                                             @RequestBody AddPurchaseItemRequest addPurchaseItemRequest) {
        PurchaseItem purchaseItem = new PurchaseItem(addPurchaseItemRequest);
        purchaseService.addPurchaseItem(purchaseItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<?> bulkDeletePurchaseItems(@NotEmpty(message = "Input id list of purchase items can not be empty")
                                                     @RequestParam("id") List<Long> idList) {
        purchaseService.bulkDeletePurchaseItems(idList);

        return ResponseEntity.ok().build();
    }
}
