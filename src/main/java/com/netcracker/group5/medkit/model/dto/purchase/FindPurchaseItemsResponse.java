package com.netcracker.group5.medkit.model.dto.purchase;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;

import java.util.List;

public class FindPurchaseItemsResponse {

    private List<PurchaseItem> purchaseItems;

    public FindPurchaseItemsResponse(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
