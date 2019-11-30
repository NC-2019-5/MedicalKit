package com.netcracker.group5.medkit.model.domain.purchase;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return purchaseItems.equals(purchase.purchaseItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseItems);
    }
}
