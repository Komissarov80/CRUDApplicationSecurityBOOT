package org.exampels.Services;

import org.exampels.dao.DaoRole;
import org.exampels.dao.DaoUser;
import org.exampels.model.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServicesImpl implements UserServices, UserDetailsService {

    @Autowired
    private DaoUser daoUser;

    @Override
    public List<User> getAllUsers() {
        List<User> list = daoUser.getAllUsers();
        for (User user : list) {
            Hibernate.initialize(user.getRoles());
        }
        return daoUser.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        daoUser.addUser(user);
    }

    @Override
    public void editUser(User user) {
    daoUser.editUser(user);
    }

    @Override
    public void deletUser(Long id) {
    daoUser.deletUser(id);
    }

    @Override
    public User getUser(Long id) {
        return daoUser.getUser(id);
    }

    @Override
    public User getUserByUserName(String name) {
        return daoUser.getUserByUserName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return daoUser.getUserByUserName(s).getUserDetails();
    }
}
