package com.progressoft.interns.advanced.utility;

import com.progressoft.interns.advanced.exception.UtilityException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParsedDataUtilityImpl implements ParsedDataUtility<List<HashMap<String, String>>> {
    @Override
    public BigDecimal getSummationOfColumn(List<HashMap<String, String>> parsedData, String columnName) {
        checkColumnName(parsedData, columnName);

        BigDecimal sum = BigDecimal.ZERO;
        for (HashMap<String, String> dataMap : parsedData) {
            String value = dataMap.get(columnName);
            BigDecimal columnValue = new BigDecimal(value);
            sum = sum.add(columnValue);
        }
        return sum.setScale(5, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getAverageOfColumn(List<HashMap<String, String>> parsedData, String columnName) {
        checkColumnName(parsedData, columnName);

        BigDecimal sum = BigDecimal.ZERO;
        int count = 0;
        for (HashMap<String, String> dataMap : parsedData) {
            String value = dataMap.get(columnName);
            BigDecimal columnValue = new BigDecimal(value);
            sum = sum.add(columnValue);
            count++;
        }
        return sum.divide(BigDecimal.valueOf(count), 5, RoundingMode.HALF_UP);
    }

    @Override
    public ArrayList<String> getColumnData(List<HashMap<String, String>> parsedData, String columnName) {
        checkColumnName(parsedData, columnName);

        ArrayList<String> columnData = new ArrayList<>();
        for (HashMap<String, String> dataMap : parsedData) {
            String value = dataMap.get(columnName);
            columnData.add(value);

        }
        return columnData;
    }

    private void checkColumnName(List<HashMap<String, String>> parsedData, String columnName) {
        for (HashMap<String, String> dataMap : parsedData) {
            if (!dataMap.containsKey(columnName)) {
                throw new UtilityException("Column " + columnName + " not found");
            }
        }
    }
}
