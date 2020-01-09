package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface PurchaseService {
    List<PurchaseItem> findPurchaseItems(Pageable pageable, String searchQuery);

    void addPurchaseItem(PurchaseItem purchaseItem);

    void deletePurchaseItem(Long id);
}
