package com.netcracker.group5.medkit.model.dto.purchase;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;

public class AddPurchaseItemRequest {

    private PurchaseItem purchaseItem;

    public AddPurchaseItemRequest(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public PurchaseItem getPurchaseItem() {
        return purchaseItem;
    }
}
