package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<PurchaseItem> findPurchaseItems(Pageable pageable, String searchQuery) {
        return purchaseRepository.findPurchaseItems(pageable.getPageSize(), pageable.getOffset(), searchQuery).orElse(null);
    }
}
