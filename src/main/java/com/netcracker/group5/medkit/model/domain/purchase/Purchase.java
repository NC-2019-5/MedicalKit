package com.netcracker.group5.medkit.model.domain.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Purchase {

    private List<PurchaseItem> purchaseItems;
}
