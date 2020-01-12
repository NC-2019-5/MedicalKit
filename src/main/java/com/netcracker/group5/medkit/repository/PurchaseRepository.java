package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    Optional<List<PurchaseItem>> findPurchaseItems(Long patientId, long limit, long offset, String searchQuery);

    PurchaseItem save(Long patientId, PurchaseItem purchaseItem);

    void removePurchaseItem(Long id);
}
