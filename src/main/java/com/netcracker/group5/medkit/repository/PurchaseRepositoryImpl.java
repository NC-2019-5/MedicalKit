package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.SqlValue;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Service
public class PurchaseRepositoryImpl implements PurchaseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Optional<List<PurchaseItem>> findPurchaseItems(Long patientId, long limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_id", patientId)
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
                                }),
                        new SqlOutParameter("p_medicine_names", OracleTypes.ARRAY, "ARRAY_OF_STRINGS",
                                ((callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(7);
                                    String[] medicineNames = (String[]) sqlArray.getArray();

                                    return Arrays.asList(medicineNames);
                                })),
                        new SqlOutParameter("p_medicine_manufacturers", OracleTypes.ARRAY, "ARRAY_OF_STRINGS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(8);
                                    String[] medicineManufacturers = (String[]) sqlArray.getArray();

                                    return Arrays.asList(medicineManufacturers);
                                }))
                .execute(parameterSource);

        List<Long> idList = (List<Long>) result.get("p_purchase_item_id_array");
        List<Long> medicineIdList = (List<Long>) result.get("p_medicine_id_array");
        List<Integer> amountList = (List<Integer>) result.get("p_amount_array");
        List<String> medicineNames = (List<String>) result.get("p_medicine_names");
        List<String> medicineManufacturers = (List<String>) result.get("p_medicine_manufacturers");

        List<PurchaseItem> purchaseItems = new ArrayList<>();
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

        return Optional.of(purchaseItems);
    }

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

        return PurchaseItem.newBuilder()
                .setId(((BigDecimal) result.get("p_purchase_item_id")).longValue())
                .setMedicine(Medicine.newBuilder()
                        .setId(((BigDecimal) result.get("p_medicine_id")).longValue())
                        .build())
                .setAmount(((BigDecimal) result.get("p_amount")).intValue())
                .build();
    }

    @Override
    public void removePurchaseItem(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_purchase_item_id", id);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PURCHASE_PKG")
                .withProcedureName("deletePurchaseItem")
                .execute(parameterSource);
    }

    @Override
    public void bulkDeletePurchaseItems(List<Long> idList) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_purchase_item_id_array", new SqlValue() {
                    @Override
                    public void setValue(PreparedStatement preparedStatement, int i) throws SQLException {
                        Connection connection = preparedStatement.getConnection();

                        if (connection.isWrapperFor(OracleConnection.class)) {
                            OracleConnection oracleConnection = connection.unwrap(OracleConnection.class);

                            ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("ARRAY_OF_NUMBERS", oracleConnection);
                            ARRAY arrayToPass = new ARRAY(arrayDescriptor, oracleConnection, idList.toArray());

                            preparedStatement.setArray(1, arrayToPass);
                        }
                    }

                    @Override
                    public void cleanup() {
                    }
                });

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PURCHASE_PKG")
                .withProcedureName("bulkDeletePurchaseItems")
                .declareParameters(
                        new SqlParameter("p_purchase_item_id_array", OracleTypes.ARRAY, "ARRAY_OF_NUMBERS"))
                .execute(parameterSource);
    }
}
