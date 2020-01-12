package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public Optional<List<PurchaseItem>> findPurchaseItems(Long patientId, int limit, long offset, String searchQuery) {
        List<PurchaseItem> purchaseItems = new ArrayList<>();

        System.out.println("limit = " + (offset + limit));
        System.out.println("offset = " + offset);

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_id", 3L)
                .addValue("limit", offset + limit)
                .addValue("offset", offset);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PURCHASE_PKG")
                .withProcedureName("getPurchaseItems")
                .declareParameters(
                        new SqlOutParameter("p_purchase_item_id_array", OracleTypes.ARRAY, "ARRAY_OF_NUMBERS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(4);
                                    BigDecimal[] bigDecIds = (BigDecimal[]) sqlArray.getArray();
                                    List<Long> idList = new ArrayList<>();

                                    for (BigDecimal bigDecId : bigDecIds) {
                                        idList.add(bigDecId.longValue());
                                    }

                                    return idList;
                                }),
                        new SqlOutParameter("p_medicine_id_array", OracleTypes.ARRAY, "ARRAY_OF_NUMBERS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(5);
                                    BigDecimal[] bigDecMedicineIds = (BigDecimal[]) sqlArray.getArray();
                                    List<Long> medicineIdList = new ArrayList<>();

                                    for (BigDecimal bigDecMedicineId : bigDecMedicineIds) {
                                        medicineIdList.add(bigDecMedicineId.longValue());
                                    }

                                    return medicineIdList;
                                }),
                        new SqlOutParameter("p_amount_array", OracleTypes.ARRAY, "ARRAY_OF_NUMBERS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(6);
                                    BigDecimal[] bigDecAmounts = (BigDecimal[]) sqlArray.getArray();
                                    List<Integer> amountList = new ArrayList<>();

                                    for (BigDecimal bigDecAmount : bigDecAmounts) {
                                        amountList.add(bigDecAmount.intValue());
                                    }

                                    return amountList;
                                }))
                .execute(parameterSource);

        System.out.println("result = " + result);

        List<Long> idList = (List<Long>) result.get("p_purchase_item_id_array");
        List<Long> medicineIdList = (List<Long>) result.get("p_medicine_id_array");
        List<Integer> amountList = (List<Integer>) result.get("p_amount_array");

        for (Long id : idList) {
            Medicine medicine = Medicine.newBuilder()
                    .setId(medicineIdList.get(idList.indexOf(id)))
                    .build();

            PurchaseItem purchaseItem = PurchaseItem.newBuilder()
                    .setId(id)
                    .setAmount(amountList.get(idList.indexOf(id)))
                    .setMedicine(medicine)
                    .build();

            purchaseItems.add(purchaseItem);
        }

        return Optional.of(purchaseItems);
    }

    @Override
    public PurchaseItem save(PurchaseItem purchaseItem) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_purchase_item_id", purchaseItem.getId())
                .addValue("p_medicine_id", purchaseItem.getMedicine().getId())
                .addValue("p_amount", purchaseItem.getAmount())
                .addValue("p_patient_id", 3L);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PURCHASE_PKG")
                .withProcedureName("savePurchaseItem")
                .execute(parameterSource);

        System.out.println("result = " + result);

        return null;
    }

    @Override
    public PurchaseItem removePurchaseItem(Long id) {
        return null;
    }
}
