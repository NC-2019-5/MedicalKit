package com.netcracker.group5.medkit.model.dto.purchase;

import com.netcracker.group5.medkit.model.domain.purchase.Purchase;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;

import java.util.List;

public class PurchaseItemResponseItem {

    private List<PurchaseItem> purchaseItems;

    public PurchaseItemResponseItem(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
