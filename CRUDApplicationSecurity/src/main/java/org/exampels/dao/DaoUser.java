package org.exampels.dao;

import org.exampels.model.User;

import java.util.List;

public interface DaoUser {
    List<User> getAllUsers();
    boolean addUser(User user);
    void editUser(User user);
    void deletUser(Long id);
    User getUser(Long id);
    User getUserByUserName(String userName);

}
