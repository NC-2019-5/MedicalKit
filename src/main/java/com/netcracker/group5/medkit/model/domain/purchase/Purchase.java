package com.netcracker.group5.medkit.model.domain.purchase;

import java.util.List;

public class Purchase {

    private List<PurchaseItem> purchaseItems;

    public Purchase(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
