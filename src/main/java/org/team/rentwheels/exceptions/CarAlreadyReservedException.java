package org.team.rentwheels.exceptions;

public class CarAlreadyReservedException extends Exception{
    public CarAlreadyReservedException(String message) {
        super(message);
    }
}
