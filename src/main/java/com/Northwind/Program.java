package com.Northwind;

import com.Northwind.data.CustomerDao;
import com.Northwind.data.ShipperDao;
import com.Northwind.data.SupplierDao;
import com.Northwind.model.Customer;
import com.Northwind.model.Shipper;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        CustomerDao customerDao = new CustomerDao(dataSource);
        ShipperDao shipperDao = new ShipperDao(dataSource);




        SupplierDao supplierDao = new SupplierDao(dataSource);

        List<Customer> customers = customerDao.getAll();
        List<Shipper> shippers = shipperDao.getAll();
     //    System.out.println(customers);

     //   System.out.println(customerDao.find("WOLZA"));
        Shipper shipper1 = new Shipper();
        Shipper shipper = new Shipper();

//       shipper1.setCompanyName("YearUp");
//        shipper.setCompanyName("Mine");

      // shipperDao.add(shipper);
      // shipperDao.add(shipper1);
        System.out.println(shipperDao.getAll());
        shipperDao.delete(7);


    }
}
