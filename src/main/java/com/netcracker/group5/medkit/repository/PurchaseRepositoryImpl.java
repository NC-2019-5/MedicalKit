package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseRepositoryImpl implements PurchaseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Optional<List<PurchaseItem>> findPurchaseItems(int limit, long offset, String searchQuery) {
        List<PurchaseItem> purchaseItems = new ArrayList<>(3);

        Medicine medicine1 = Medicine.newBuilder()
                .setName("med 1")
                .build();

        Medicine medicine2 = Medicine.newBuilder()
                .setName("med 2")
                .build();

        Medicine medicine3 = Medicine.newBuilder()
                .setName("med 3")
                .build();

        purchaseItems.add(PurchaseItem.newBuilder()
                .setMedicine(medicine1)
                .setAmount(2)
                .setId(1L)
                .build());

        purchaseItems.add(PurchaseItem.newBuilder()
                .setMedicine(medicine2)
                .setAmount(15)
                .setId(2L)
                .build());

        purchaseItems.add(PurchaseItem.newBuilder()
                .setMedicine(medicine3)
                .setAmount(5)
                .setId(3L)
                .build());

        return Optional.of(purchaseItems);
    }

    @Override
    public PurchaseItem save(PurchaseItem purchaseItem) {
        return null;
    }

    @Override
    public PurchaseItem removePurchaseItem(Long id) {
        return null;
    }
}
