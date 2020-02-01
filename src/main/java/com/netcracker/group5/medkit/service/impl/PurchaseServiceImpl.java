package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.PurchaseRepository;
import com.netcracker.group5.medkit.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<PurchaseItem> findPurchaseItems(Pageable pageable, String searchQuery) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        long limit = pageable.getOffset() + pageable.getPageSize();

        return purchaseRepository.findPurchaseItems(currentUser.getId(), limit, pageable.getOffset(), searchQuery);
    }

    @Override
    public PurchaseItem savePurchaseItem(PurchaseItem purchaseItem) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return purchaseRepository.save(currentUser.getId(), purchaseItem);
    }

    @Override
    public void bulkDeletePurchaseItems(List<Long> idList) {
        purchaseRepository.bulkDeletePurchaseItems(idList);
    }
}
