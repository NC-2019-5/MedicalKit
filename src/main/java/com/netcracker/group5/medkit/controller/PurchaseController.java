package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemRequest;
import com.netcracker.group5.medkit.model.dto.purchase.FindPurchaseItemsResponse;
import com.netcracker.group5.medkit.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/profile/purchases")
    public ResponseEntity<?> findPurchaseItems(@PageableDefault Pageable pageable, @RequestParam(required = false) String searchQuery) {
        List<PurchaseItem> purchaseItems = purchaseService.findPurchaseItems(pageable, searchQuery);
        FindPurchaseItemsResponse responseItem = new FindPurchaseItemsResponse(purchaseItems);

        return ResponseEntity.ok(responseItem);
    }

    @PostMapping("/profile/purchases")
    public ResponseEntity<?> addPurchaseItem(@RequestBody AddPurchaseItemRequest addPurchaseItemRequest) {
        PurchaseItem purchaseItem = new PurchaseItem(addPurchaseItemRequest);
        purchaseService.addPurchaseItem(purchaseItem);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile/purchases")
    public ResponseEntity<?> deletePurchaseItem(@RequestParam Long id) {
        purchaseService.deletePurchaseItem(id);

        return ResponseEntity.ok().build();
    }
}
