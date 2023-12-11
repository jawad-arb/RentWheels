package org.team.rentwheels.utils;

import javafx.util.StringConverter;
import org.team.rentwheels.models.Customer;

public class CustomerNameConverter extends StringConverter<Customer> {
    @Override
    public String toString(Customer customer) {
        return customer.getFirstName()+" "+customer.getLastName();
    }

    @Override
    public Customer fromString(String s) {
        return null;
    }
}
