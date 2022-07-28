package sait.boi.helpers;

import java.sql.*;
import java.util.*;

/**
 * Provides information about a ResultSet from the database.
 * DO NOT modify this class!
 * @author Nick Hamnett
 * @version Aug 7, 2021
 */
public final class ResultSetHelper {
    private int totalColumns;
    private String[] columnNames;

    private int totalRows;

    private ArrayList<String[]> rows;

    /**
     * Inititalizes the ResultSet Helper
     * @param resultSet
     */
    public ResultSetHelper(ResultSet resultSet) throws SQLException {
        this.parse(resultSet);
    }

    /**
     * Gets the total # of columns in the ResultSet
     * @return # of columns
     */
    public int getTotalColumns() {
        return this.totalColumns;
    }

    /**
     * Gets the names of the columns
     * @return Column names
     */
    public String[] getColumnNames() {
        return this.columnNames;
    }

    /**
     * Gets the # of rows found
     * @return # of rows
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * Gets an array of rows (with each row being an array of the column values)
     * @return Array of rows
     */
    public String[][] getRows() {
        return (String[][]) this.rows.toArray();
    }

    /**
     * Gets row at index
     * @param index Index of row
     * @return Row values
     */
    public String[] getRow(int index) {
        return this.rows.get(index);
    }

    /**
     * Parses the ResultSet
     * @throws SQLException Thrown if there's an error accessing the result.
     */
    private void parse(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        this.totalColumns = metaData.getColumnCount();

        this.columnNames = new String[this.totalColumns];

        for (int i = 0; i < this.columnNames.length; i++) {
            this.columnNames[i] = metaData.getColumnName(i + 1);
        }

        if (!resultSet.isBeforeFirst())
            resultSet.beforeFirst();

        this.totalRows = 0;

        this.rows = new ArrayList<>();

        while (resultSet.next()) {
            String[] row = new String[this.totalColumns];

            for (int col = 0; col < this.totalColumns; col++) {
                Object value = resultSet.getObject(col + 1);

                row[col] = value.toString();
            }

            rows.add(row);

            this.totalRows++;
        }
    }
}
