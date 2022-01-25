package org.exampels.Services;

import org.exampels.model.User;

import java.util.List;

public interface UserServices {
    List<User> getAllUsers();
    void addUser(User user);
    void editUser(User user);
    void deletUser(Long id);
    User getUser(Long id);
    User getUserByUserName(String name);
}
