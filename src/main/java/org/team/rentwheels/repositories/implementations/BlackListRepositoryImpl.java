package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.models.BlackList;
import org.team.rentwheels.repositories.BlackListRepository;
import org.team.rentwheels.utils.DatabaseOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.team.rentwheels.queries.BlackListQuery.*;

public class BlackListRepositoryImpl implements BlackListRepository {
    DatabaseOperations dbOperations=new DatabaseOperations();
    @Override
    public void addItemToBlackList(BlackList blackListItem) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(ADD_CUSTOMER_TO_BLACK_LIST);
        ps.setInt(1,blackListItem.getCustomerId());
        ps.setInt(2,blackListItem.getCarId());
        ps.setDate(3,blackListItem.getStartDate());
        ps.setDate(4,blackListItem.getEndDate());
        ps.setString(5,blackListItem.getReason());
        ps.executeUpdate();
    }

    @Override
    public void deleteItemFromBlackList(int entry_id) throws SQLException {
        if(getItemFromBlackListWithId(entry_id)==null)
            throw new RuntimeException("the is is not exists");
        PreparedStatement ps=dbOperations.setConnection(DELETE_FROM_BLACK_LIST);
        ps.setInt(1,entry_id);
        ps.executeUpdate();
    }

    @Override
    public void updateItemFromBlackList(int entry_id, BlackList updatedBlackListItem) throws SQLException,RuntimeException {
        PreparedStatement ps =dbOperations.setConnection(UPDATE_FROM_BLACK_LIST_BY_ENTRY_ID);
        ps.setInt(1,updatedBlackListItem.getCustomerId());
        ps.setInt(2,updatedBlackListItem.getCarId());
        ps.setDate(3,updatedBlackListItem.getStartDate());
        ps.setDate(4,updatedBlackListItem.getEndDate());
        ps.setString(5,updatedBlackListItem.getReason());
        ps.setInt(6,entry_id);
        ps.executeUpdate();
    }

    @Override
    public int getNbrCustomersInBlackList() throws SQLException {
        PreparedStatement ps= dbOperations.setConnection(GET_NUMBER_OF_CUSTOMERS_IN_BLACK_LIST);
        ResultSet rs =ps.executeQuery();
        if(!rs.next())
            return 0;
        return rs.getInt(1);
    }

    @Override
    public BlackList getItemFromBlackListWithId(int entry_id) throws SQLException {
        PreparedStatement ps =dbOperations.setConnection(GET_ITEM_FROM_BLACK_LIST_BY_ENTRY_ID);
        ps.setInt(1,entry_id);
        ResultSet rs=ps.executeQuery();
        if (!rs.next())
            return null;

        BlackList blackList=new BlackList();
        blackList.setCustomerId(rs.getInt("customer_id"));
        blackList.setCarId(rs.getInt("car_id"));
        blackList.setStartDate(rs.getDate("start_date"));
        blackList.setEndDate(rs.getDate("end_date"));
        blackList.setReason(rs.getString("reason"));
        return blackList;
    }

    /**
     *
     * @param customer_id
     * @return true --> customer Exist in BlackList
     * @throws SQLException
     */
    @Override
    public boolean checkIfCustomerExistInBlackList(int customer_id) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(CHECK_IF_CUSTOMER_EXISTS_IN_BLACK_LIST);
        ps.setInt(1,customer_id);
        ResultSet rs=ps.executeQuery();
        if(!rs.next())
            return false;
        int count=rs.getInt(1);
            return count > 0;
    }

    @Override
    public List<BlackList> getAllItemsInBlackList() throws SQLException {
        List<BlackList> blackListItems=new ArrayList<>();
        PreparedStatement ps=dbOperations.setConnection(GET_ALL_ITEMS_IN_BLACK_LIST);
        ResultSet rs =ps.executeQuery();
        while (rs.next()){
            BlackList blackListItem = new BlackList();
            blackListItem.setId(rs.getInt("entry_id"));
            blackListItem.setCustomerId(rs.getInt("customer_id"));
            blackListItem.setCarId(rs.getInt("car_id"));
            blackListItem.setStartDate(rs.getDate("start_date"));
            blackListItem.setEndDate(rs.getDate("end_date"));
            blackListItem.setReason(rs.getString("reason"));
            blackListItems.add(blackListItem);
        }
        return blackListItems;
    }
}
