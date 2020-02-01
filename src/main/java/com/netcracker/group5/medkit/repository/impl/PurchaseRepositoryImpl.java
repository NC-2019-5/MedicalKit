package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.repository.PurchaseRepository;
import com.netcracker.group5.medkit.util.SqlArray;
import com.netcracker.group5.medkit.util.SqlReturnListFromArray;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Service
public class PurchaseRepositoryImpl implements PurchaseRepository {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public List<PurchaseItem> findPurchaseItems(Long patientId, long limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_id", patientId)
                .addValue("limit", limit)
                .addValue("offset", offset);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PURCHASE_PKG")
                .withProcedureName("getPurchaseItems")
                .declareParameters(
                        new SqlOutParameter("p_purchase_item_id_array", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_medicine_id_array", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_amount_array", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Integer.class)),
                        new SqlOutParameter("p_medicine_names", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_manufacturers", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)))
                .execute(parameterSource);

        List<Long> idList = (List<Long>) result.get("p_purchase_item_id_array");
        List<Long> medicineIdList = (List<Long>) result.get("p_medicine_id_array");
        List<Integer> amountList = (List<Integer>) result.get("p_amount_array");
        List<String> medicineNames = (List<String>) result.get("p_medicine_names");
        List<String> medicineManufacturers = (List<String>) result.get("p_medicine_manufacturers");

        List<PurchaseItem> purchaseItems = new ArrayList<>(idList.size());
        ListIterator<Long> iterator = idList.listIterator();

        while (iterator.hasNext()) {
            Medicine medicine = Medicine.newBuilder()
                    .setId(medicineIdList.get(iterator.nextIndex()))
                    .setName(medicineNames.get(iterator.nextIndex()))
                    .setManufacturer(medicineManufacturers.get(iterator.nextIndex()))
                    .build();

            PurchaseItem purchaseItem = PurchaseItem.newBuilder()
                    .setAmount(amountList.get(iterator.nextIndex()))
                    .setId(iterator.next())
                    .setMedicine(medicine)
                    .build();

            purchaseItems.add(purchaseItem);
        }
        logger.info("Purchase items found!");
        return purchaseItems;
    }

    @Transactional
    @Override
    public PurchaseItem save(Long patientId, PurchaseItem purchaseItem) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_purchase_item_id", purchaseItem.getId())
                .addValue("p_medicine_id", purchaseItem.getMedicine().getId())
                .addValue("p_amount", purchaseItem.getAmount())
                .addValue("p_patient_id", patientId);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PURCHASE_PKG")
                .withProcedureName("savePurchaseItem")
                .execute(parameterSource);
        logger.info("Purchase item saved!");

        return PurchaseItem.newBuilder()
                .setId(((BigDecimal) result.get("p_purchase_item_id")).longValue())
                .setMedicine(Medicine.newBuilder()
                        .setId(((BigDecimal) result.get("p_medicine_id")).longValue())
                        .setName((String) result.get("p_medicine_name"))
                        .setManufacturer((String) result.get("p_medicine_manufacturer"))
                        .build())
                .setAmount(((BigDecimal) result.get("p_amount")).intValue())
                .build();
    }

    @Transactional
    @Override
    public void bulkDeletePurchaseItems(List<Long> idList) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_purchase_item_id_array", SqlArray.of(idList, SqlArray.ARRAY_OF_NUMBERS));

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PURCHASE_PKG")
                .withProcedureName("bulkDeletePurchaseItems")
                .declareParameters(
                        new SqlParameter("p_purchase_item_id_array", OracleTypes.ARRAY, SqlArray.ARRAY_OF_NUMBERS))
                .execute(parameterSource);
        logger.info("Purchase items deleted!");
    }
}
