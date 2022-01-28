package org.exampels.dao;

import org.exampels.Services.RoleService;
import org.exampels.model.Role;
import org.exampels.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class DaoUserlmpl implements DaoUser{

    @PersistenceContext
    private EntityManager sessionFactory;

    @Autowired
    private RoleService roleService;

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public boolean addUser(User user) {
        String nameUser = user.getName();
        List<User> usersList = getAllUsers();
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getName().equals(nameUser)) {
                return false;
            }
        }
        List<Role> roleList = roleService.listRole();
        if (user.getRoles().contains("ROLE_ADMIN")) {
            for (int i = 0; i < roleList.size(); i ++) {
                if (roleList.get(i).getRole().equals("ROLE_ADMIN")) {
                    user.addRole(roleList.get(i));
                }
                if (roleList.get(i).getRole().equals("ROLE_USER")) {
                    user.addRole(roleList.get(i));
                }
            }
        }
        sessionFactory.persist(user);
        return true;
    }

    @Override
    public void editUser(User user) {
        sessionFactory.merge(user);
    }

    @Override
    public void deletUser(Long id) {
        User user = sessionFactory.find(User.class, id);
        sessionFactory.remove(user);
    }

    @Override
    public User getUser(Long id) {
        User user = sessionFactory.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id", User.class)
                .setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = sessionFactory.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.name = :userName", User.class)
                .setParameter("userName", userName).getSingleResult();
        return user;
    }
}
