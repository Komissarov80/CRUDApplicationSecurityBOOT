package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User employee, String[] roles);
    List<User> getAllUser();
    User getUser(int id);
    User getUser(String username);
    void updateUser(int id, User user, String[] roles);
    void deleteUser(int id);
}
