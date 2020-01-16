package com.netcracker.group5.medkit.util;

import oracle.jdbc.OracleConnection;
import org.springframework.jdbc.support.SqlValue;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SqlArray implements SqlValue {

    public static final String ARRAY_OF_NUMBERS = "ARRAY_OF_NUMBERS";
    public static final String ARRAY_OF_STRINGS = "ARRAY_OF_STRINGS";
    public static final String ARRAY_OF_DATES = "ARRAY_OF_DATES";

    private List<?> list;
    private String arrayType;

    private SqlArray(List<?> list, String arrayType) {
        this.list = list;
        this.arrayType = arrayType;
    }

    @Override
    public void setValue(PreparedStatement preparedStatement, int i) throws SQLException {
        Connection connection = preparedStatement.getConnection();

        if (connection.isWrapperFor(OracleConnection.class)) {
            OracleConnection oracleConnection = connection.unwrap(OracleConnection.class);
            Array arrayToPass = oracleConnection.createOracleArray(arrayType, list.toArray());

            preparedStatement.setArray(i, arrayToPass);
        }
    }

    @Override
    public void cleanup() {

    }

    public static SqlArray of(List<?> list, String arrayType) {
        return new SqlArray(list, arrayType);
    }
}
