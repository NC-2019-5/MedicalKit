package com.netcracker.group5.medkit.model.domain.purchase;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseItem {

    private long id;
    private Medicine medicine;
    private int amount;
}
