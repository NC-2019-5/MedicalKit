package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    Optional<List<PurchaseItem>> findPurchaseItems(int limit, long offset, String searchQuery);

    PurchaseItem save(PurchaseItem purchaseItem);

    PurchaseItem removePurchaseItem(Long id);
}
