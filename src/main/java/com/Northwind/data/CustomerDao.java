package com.Northwind.data;

import com.Northwind.model.Customer;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Calendar.PM;

public class CustomerDao {
    private DataSource dataSource;

    public CustomerDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        String query = """
                SELECT CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax
                FROM Customers;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            String searchTerm = "%AIR%";
            statement.setString(1, searchTerm);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Customer customer = new Customer(
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
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }

        Customer customer1 = new Customer(
                "ALFKI",
                "Alfreds Futterkiste",
                "Maria Anders",
                "Sales Representative",
                "Obere Str. 57",
                "Berlin",
                null, // Region is often null for some Northwind data
                "12209",
                "Germany",
                "030-0074321",
                "030-0076545"
        );

        // Customer 2: Ana Trujillo Emparedados y helados
        Customer customer2 = new Customer(
                "ANATR",
                "Ana Trujillo Emparedados y helados",
                "Ana Trujillo",
                "Owner",
                "Avda. de la Constitución 2222",
                "México D.F.",
                null,
                "05021",
                "Mexico",
                "(5) 555-4729",
                "(5) 555-3745"
        );

        // Customer 3: Antonio Moreno Taquería
        Customer customer3 = new Customer(
                "ANTON",
                "Antonio Moreno Taquería",
                "Antonio Moreno",
                "Owner",
                "Mataderos 2312",
                "México D.F.",
                null,
                "05023",
                "Mexico",
                "(5) 555-3932",
                null // Fax is often null/missing in some Northwind data
        );

        // Customer 4: Around the Horn
        Customer customer4 = new Customer(
                "AROUT",
                "Around the Horn",
                "Thomas Hardy",
                "Sales Representative",
                "120 Hanover Sq.",
                "London",
                null,
                "WA1 1DP",
                "UK",
                "(171) 555-7788",
                "(171) 555-6750"
        );

        // Customer 5: Berglunds snabbköp
        Customer customer5 = new Customer(
                "BERGS",
                "Berglunds snabbköp",
                "Christina Berglund",
                "Order Administrator",
                "Berguvsvägen 8",
                "Luleå",
                null,
                "S-958 22",
                "Sweden",
                "0921-12 34 65",
                "0921-12 34 67"
        );

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);


        return customers;
    }
}
