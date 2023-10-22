package org.team.rentwheels.services;

import org.team.rentwheels.models.User;
import org.team.rentwheels.repositories.UserRepository;
import org.team.rentwheels.repositories.implementations.UserRepositoryImpl;

import java.sql.SQLException;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository=new UserRepositoryImpl();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserIfExist(String username,String password) throws SQLException {
        return userRepository.getUserIfExist(username,password);
    }



}
