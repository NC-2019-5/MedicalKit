package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;

import java.util.List;

public interface PurchaseRepository {
    List<PurchaseItem> findPurchaseItems(Long patientId, long limit, long offset, String searchQuery);

    PurchaseItem save(Long patientId, PurchaseItem purchaseItem);

    void bulkDeletePurchaseItems(List<Long> idList);
}
