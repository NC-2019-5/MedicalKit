package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PurchaseItem> findPurchaseItems(Pageable pageable, String searchQuery) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByUserId(currentUser.getId());

        long limit = pageable.getOffset() + pageable.getPageSize();

        return purchaseRepository.findPurchaseItems(patient.getId(), limit, pageable.getOffset(), searchQuery);
    }

    @Override
    public void addPurchaseItem(PurchaseItem purchaseItem) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByUserId(currentUser.getId());

        purchaseRepository.save(patient.getId(), purchaseItem);
    }

    @Override
    public void deletePurchaseItem(Long id) {
        purchaseRepository.removePurchaseItem(id);
    }

    @Override
    public void bulkDeletePurchaseItems(List<Long> idList) {
        purchaseRepository.bulkDeletePurchaseItems(idList);
    }
}
