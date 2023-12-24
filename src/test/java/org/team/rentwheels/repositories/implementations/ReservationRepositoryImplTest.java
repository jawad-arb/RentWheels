package org.team.rentwheels.repositories.implementations;

import org.junit.jupiter.api.Test;
import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.repositories.ReservationRepository;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryImplTest {
    private final ReservationRepository reservationRepository=new ReservationRepositoryImpl();

    @Test
    void getCarIdByReservation() throws SQLException {
        assertEquals(2,reservationRepository.getCarIdByReservation(1));
    }
}