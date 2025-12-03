package com.Northwind.data;

import javax.sql.DataSource;

public class CustomerDao {
    private DataSource dataSource;

    public CustomerDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
