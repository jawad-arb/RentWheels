package org.team.rentwheels.services;

import org.team.rentwheels.exceptions.CustomerAlreadyExistsException;
import org.team.rentwheels.exceptions.CustomerNotExistsException;
import org.team.rentwheels.models.Customer;
import org.team.rentwheels.repositories.CustomerRepository;
import org.team.rentwheels.repositories.implementations.CustomerRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository=new CustomerRepositoryImpl();
    }

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(String firstName,
                            String lastName,
                            String email,
                            String phone,
                            String address) throws SQLException, CustomerAlreadyExistsException {
        if (customerAlreadyExists(email))
            throw new CustomerAlreadyExistsException("Customer Already Exist");
        customerRepository.addCustomer(firstName,
                lastName,
                email,
                phone,
                address);
    }

    public void deleteCustomer(int id) throws SQLException, CustomerNotExistsException {
        if (!customerExists(id))
            throw new CustomerNotExistsException("Customer not exists");
        customerRepository.deleteCustomer(id);
    }

    public void updateCustomer(int id,
                               Customer updatedCustomer) throws SQLException, CustomerNotExistsException {
        if (!customerExists(id))
            throw new CustomerNotExistsException("Customer not exists");
        customerRepository.updateCustomer(id,updatedCustomer);
    }

    public Customer getCustomerById(int id) throws SQLException, CustomerNotExistsException {
        if (!customerExists(id))
            throw new CustomerNotExistsException("Customer not exists");
        return customerRepository.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerRepository.getAllCustomers();
    }

    public boolean customerAlreadyExists(String email) throws SQLException {
        return this.customerRepository.doesCustomerExists(email);
    }
    public boolean customerExists(int id) throws SQLException {
        return this.customerRepository.customerExists(id);
    }

    }
