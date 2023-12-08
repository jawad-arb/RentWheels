package org.team.rentwheels.repositories.implementations;

import org.junit.jupiter.api.Test;
import org.team.rentwheels.repositories.CustomerRepository;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {
    private CustomerRepository customerRepository=new CustomerRepositoryImpl();

    @Test
    void doesCustomerExists() throws SQLException {
        String email="john@example.com";
        assertTrue(customerRepository.doesCustomerExists(email));
    }
    @Test
    void customerNotExists() throws SQLException {
        assertTrue(customerRepository.customerExists(2));
    }
}
