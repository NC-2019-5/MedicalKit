package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.dto.purchase.PurchaseItemResponseItem;
import com.netcracker.group5.medkit.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin
@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/profile/purchases")
    public ResponseEntity<?> findPurchaseItems(@PageableDefault Pageable pageable, String searchQuery) {
        PurchaseItemResponseItem responseItem = new PurchaseItemResponseItem(purchaseService.findPurchaseItems(pageable, searchQuery));
        return ResponseEntity.ok(responseItem);
    }
}
