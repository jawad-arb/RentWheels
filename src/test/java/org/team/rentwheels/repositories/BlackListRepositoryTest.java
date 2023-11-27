package org.team.rentwheels.repositories;

import org.junit.jupiter.api.Test;
import org.team.rentwheels.DatabaseConnection;
import org.team.rentwheels.models.BlackList;
import org.team.rentwheels.repositories.implementations.BlackListRepositoryImpl;
import org.team.rentwheels.utils.DatabaseOperations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.team.rentwheels.queries.BlackListQuery.ADD_CUSTOMER_TO_BLACK_LIST;
import static org.team.rentwheels.queries.BlackListQuery.DELETE_FROM_BLACK_LIST;


class BlackListRepositoryTest {
    private BlackListRepository blackListRepository=new BlackListRepositoryImpl();
    private DatabaseOperations dbOperations=new DatabaseOperations();

    @Test
    void addItemToBlackList() throws SQLException {
        //given
        BlackList blackList=new BlackList(
                2,2, Date.valueOf("2023-11-27"),Date.valueOf("2023-12-02"),"damaged the car"
        );
        //when
        blackListRepository.addItemToBlackList(blackList);
        //then

    }

    @Test
    void deleteItemFromBlackList() throws SQLException {
        //given
        int id=13;
        //when
        blackListRepository.deleteItemFromBlackList(id);
        //then

    }

    @Test
    void updateItemFromBlackList() throws SQLException {
        //given
        BlackList updatedBlackList=new BlackList(
                2,4, Date.valueOf("2023-11-25"),Date.valueOf("2023-12-02"),"retard to retourne the car"
        );
        //when
        blackListRepository.updateItemFromBlackList(14,updatedBlackList);
        //then

    }

    @Test
    void getNbrCustomersInBlackList() throws SQLException {
        assertEquals(2,blackListRepository.getNbrCustomersInBlackList());
    }

    @Test
    void getItemFromBlackListWithId() {
    }

    @Test
    void checkIfCustomerExistInBlackList() throws SQLException {
        //given
        int id=2;
        //when
        assertTrue(blackListRepository.checkIfCustomerExistInBlackList(id));
        //then
    }

    @Test
    void getAllItemsInBlackList() {
    }
}