package org.team.rentwheels.repositories;

import org.team.rentwheels.models.BlackList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface BlackListRepository {
    void addItemToBlackList(BlackList blackListItem) throws SQLException;
    void deleteItemFromBlackList(int entry_id) throws SQLException;
    void updateItemFromBlackList(int entry_id,BlackList updatedBlackListItem) throws SQLException;
    int getNbrCustomersInBlackList() throws SQLException;
    /**
     *
     * @param entry_id blackListEntryId
     * @return BlackListItem
     */
    BlackList getItemFromBlackListWithId(int entry_id) throws SQLException;

    /**
     *
     * @param customer_id
     * @return true --> customer exist in BlackList
     * @Move no reservation for customers in the BlackList
     */
    boolean checkIfCustomerExistInBlackList(int customer_id) throws SQLException;
    List<BlackList> getAllItemsInBlackList() throws SQLException;
}
