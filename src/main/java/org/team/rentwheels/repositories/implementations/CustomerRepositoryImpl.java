package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.models.Customer;
import org.team.rentwheels.repositories.CustomerRepository;
import org.team.rentwheels.utils.DatabaseOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.team.rentwheels.queries.CustomerQuery.*;

public class CustomerRepositoryImpl implements CustomerRepository {
    DatabaseOperations dbOperations = new DatabaseOperations();
    @Override
    public void addCustomer(String firstName, String lastName, String email, String phone, String address) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(ADD_CUSTOMER_QUERY);
        ps.setString(1,firstName);
        ps.setString(2,lastName);
        ps.setString(3,email);
        ps.setString(4,phone);
        ps.setString(5,address);
        ps.executeUpdate();
        if(ps!=null){
            ps.close();
        }
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(DELETE_CUSTOMER_BY_ID);
        ps.setInt(1,id);
        ps.executeUpdate();
        if(ps!=null){
            ps.close();
        }
    }

    @Override
    public void updateCustomer(int id, Customer updatedCustomer) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(UPDATE_CUSTOMER_WHERE_ID);
        ps.setString(1,updatedCustomer.getFirstName());
        ps.setString(2,updatedCustomer.getLastName());
        ps.setString(3,updatedCustomer.getEmail());
        ps.setString(4,updatedCustomer.getPhone());
        ps.setString(5,updatedCustomer.getAddress());
        ps.setInt(6,id);
        ps.executeUpdate();
        if (ps != null) {
            System.out.println("You are good my friend");
            ps.close();
        }
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        Customer customer=new Customer();
        PreparedStatement ps =dbOperations.setConnection(GET_CUSTOMER_BY_ID);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            customer.setId(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email"));
            customer.setPhone(rs.getString("phone"));
            customer.setAddress(rs.getString("address"));

        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers=new ArrayList<>();
        PreparedStatement ps=dbOperations.setConnection(GET_ALL_CUSTOMERS);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            Customer customer=new Customer();
            customer.setId(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email"));
            customer.setPhone(rs.getString("phone"));
            customer.setAddress(rs.getString("address"));
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public boolean doesCustomerExists(String email) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(DOES_CUSTOMER_EXISTS);
        ps.setString(1,email);
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
        return false;
    }

    @Override
    public boolean customerExists(int id) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(CUSTOMER_EXISTS_BY_ID);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
        return false;
    }


}
