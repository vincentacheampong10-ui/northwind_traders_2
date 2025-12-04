package com.Northwind.data;

import com.Northwind.model.Customer;
import com.Northwind.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierDao {
    private DataSource dataSource;

    public SupplierDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();

        String query = """
                 SELECT productID, productName, supplierID,categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued
                FROM Products;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Supplier supplier = new Supplier(
                            resultSet.getString("productID"),
                            resultSet.getString("productName"),
                            resultSet.getInt("supplierID"),
                            resultSet.getInt("categoryID"),
                            resultSet.getString("quantityPerUnit"),
                            resultSet.getDouble("unitPrice"),
                            resultSet.getInt("unitsInStock"),
                            resultSet.getInt("unitsOnOrder"),
                            resultSet.getInt("reorderLevel"),
                            resultSet.getBoolean("discontinued"));

                    suppliers.add(supplier);

                }
            }

        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }

        return suppliers;

    }
}
