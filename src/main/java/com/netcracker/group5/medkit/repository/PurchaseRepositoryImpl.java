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

        for (int i = (int) offset; i < limit; i++) {
            Medicine medicine = Medicine.newBuilder()
                    .setName("med i")
                    .build();

            PurchaseItem purchaseItem = PurchaseItem.newBuilder()
                    .setId((long) i)
                    .setMedicine(medicine)
                    .setAmount(i)
                    .build();
        }

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
