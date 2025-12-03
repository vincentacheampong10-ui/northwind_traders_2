package com.Northwind.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

public class SupplierDao {
    public Supplier find(int SupplierID){
        Supplier supplier = null;
        String query = """
                SELECT *
                FROM products
                WHERE SupplierID = 1;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, SupplierID);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    supplier = new Supplier(
                            resultSet.getString("CustomerID"),
                            resultSet.getString("CompanyName"),
                            resultSet.getString("ContactName"),
                            resultSet.getString("ContactTitle"),
                            resultSet.getString("Address"),
                            resultSet.getString("City"),
                            resultSet.getString("Region"),
                            resultSet.getString("PostalCode"),
                            resultSet.getString("Country"),
                            resultSet.getString("Phone"),
                            resultSet.getString("Fax"));


                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }
}
