package org.team.rentwheels.services;

import org.team.rentwheels.models.BlackList;
import org.team.rentwheels.repositories.BlackListRepository;
import org.team.rentwheels.repositories.implementations.BlackListRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class BlackListService {
    private final BlackListRepository blackListRepository;

    public BlackListService() {
        this.blackListRepository=new BlackListRepositoryImpl();
    }

    public BlackListService(BlackListRepository blackListRepository) {
        this.blackListRepository = blackListRepository;
    }
    public void addItemToBlackList(BlackList blackList) throws SQLException {
        this.blackListRepository.addItemToBlackList(blackList);
    }
    void deleteItemFromBlackList(int entry_id) throws SQLException {
        this.blackListRepository.deleteItemFromBlackList(entry_id);
    }
    void updateItemFromBlackList(int entry_id,BlackList updatedBlackListItem) throws SQLException{
        this.blackListRepository.updateItemFromBlackList(entry_id,updatedBlackListItem);
    }
    int getNbrCustomersInBlackList() throws SQLException{
        return this.blackListRepository.getNbrCustomersInBlackList();
    }
    BlackList getItemFromBlackListWithId(int entry_id) throws SQLException{
        return this.blackListRepository.getItemFromBlackListWithId(entry_id);
    }
    public boolean  checkIfCustomerExistInBlackList(int customer_id) throws SQLException{
        return this.blackListRepository.checkIfCustomerExistInBlackList(customer_id);
    }
    List<BlackList> getAllItemsInBlackList() throws SQLException{
        return this.blackListRepository.getAllItemsInBlackList();
    }


}
