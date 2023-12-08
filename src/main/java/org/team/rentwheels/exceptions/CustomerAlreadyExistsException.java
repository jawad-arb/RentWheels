package org.team.rentwheels.exceptions;

public class CustomerAlreadyExistsException extends Exception{
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
