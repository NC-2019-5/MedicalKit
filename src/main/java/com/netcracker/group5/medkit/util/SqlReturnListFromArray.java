package com.netcracker.group5.medkit.util;

import org.springframework.jdbc.core.SqlReturnType;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlReturnListFromArray<T> implements SqlReturnType {

    private final Class<T> type;

    private SqlReturnListFromArray(Class<T> type) {
        this.type = type;
    }

    @Override
    public Object getTypeValue(CallableStatement callableStatement, int index, int i1, String oracleType) throws SQLException {
        Array sqlArray = callableStatement.getArray(index);
        List<Object> listOfValues;

        if (oracleType.equals(SqlArray.ARRAY_OF_NUMBERS)) {
            BigDecimal[] arrayOfValues = (BigDecimal[]) sqlArray.getArray();

            if (type.isAssignableFrom(Long.class)) {
                listOfValues = new ArrayList<>(arrayOfValues.length);

                for (BigDecimal value : arrayOfValues) {
                    if (value != null) {
                        listOfValues.add(value.longValue());
                    } else {
                        listOfValues.add(null);
                    }

                }

                return listOfValues;
            } else if (type.isAssignableFrom(Integer.class)) {
                listOfValues = new ArrayList<>(arrayOfValues.length);

                for (BigDecimal value : arrayOfValues) {
                    listOfValues.add(value.intValue());
                }

                return listOfValues;
            } else if (type.isAssignableFrom(Double.class)) {
                listOfValues = new ArrayList<>(arrayOfValues.length);

                for (BigDecimal value : arrayOfValues) {
                    listOfValues.add(value.doubleValue());
                }

                return listOfValues;
            }
        } else if (oracleType.equals(SqlArray.ARRAY_OF_STRINGS)) {
            if (type.isAssignableFrom(String.class)) {
                String[] medicineNames = (String[]) sqlArray.getArray();

                return Arrays.asList(medicineNames);
            }
        } else if (oracleType.equals(SqlArray.ARRAY_OF_DATES)) {
            Timestamp[] arrayOfValues = (Timestamp[]) sqlArray.getArray();

            if (type.isAssignableFrom(LocalDate.class)) {
                listOfValues = new ArrayList<>(arrayOfValues.length);

                for (Timestamp value : arrayOfValues) {
                    listOfValues.add(value.toLocalDateTime().toLocalDate());
                }
                return listOfValues;
            }else if(type.isAssignableFrom(LocalDateTime.class)){
                listOfValues = new ArrayList<>(arrayOfValues.length);

                for (Timestamp value : arrayOfValues) {
                    listOfValues.add(value.toLocalDateTime());
                }
                return listOfValues;
            }
        } else {
            throw new SQLException("Can not convert SQL type " + oracleType);
        }

        throw new IllegalArgumentException("Can not convert SQL type " + oracleType + " to type " + type.getName());
    }

    public static <T> SqlReturnListFromArray<T> of(Class<T> type) {
        return new SqlReturnListFromArray<T>(type);
    }
}
