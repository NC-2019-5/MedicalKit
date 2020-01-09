package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemRequest;
import com.netcracker.group5.medkit.model.dto.purchase.FindPurchaseItemsResponse;
import com.netcracker.group5.medkit.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/profile/purchases")
    public ResponseEntity<?> findPurchaseItems(@PageableDefault Pageable pageable, String searchQuery) {
        FindPurchaseItemsResponse responseItem = new FindPurchaseItemsResponse(purchaseService.findPurchaseItems(pageable, searchQuery));
        return ResponseEntity.ok(responseItem);
    }

    @PostMapping("/profile/add-purchase-item")
    public ResponseEntity<?> addPurchaseItem(AddPurchaseItemRequest addPurchaseItemRequest) {
        purchaseService.addPurchaseItem(addPurchaseItemRequest.getPurchaseItem());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile/delete-purchase-item")
    public ResponseEntity<?> deletePurchaseItem(@RequestParam Long id) {
        purchaseService.deletePurchaseItem(id);
        return ResponseEntity.ok().build();
    }
}
