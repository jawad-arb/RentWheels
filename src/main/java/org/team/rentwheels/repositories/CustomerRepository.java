package org.team.rentwheels.repositories;

import org.team.rentwheels.models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    //add a customer
    void addCustomer(String firstName,
                     String lastName,
                     String email,
                     String phone,
                     String address
                     ) throws SQLException;
    //delete a customer
    void deleteCustomer(int id) throws SQLException;
    //update a customer
    void updateCustomer(int id, Customer updatedCustomer) throws SQLException;
    //get a customer by id
    Customer getCustomerById(int id) throws SQLException;
    // get all customers
    List<Customer> getAllCustomers() throws SQLException;
}
