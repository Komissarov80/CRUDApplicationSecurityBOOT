package org.exampels.dao;

import org.exampels.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class DaoRoleImpl implements DaoRole {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Role> listRole() {
        return manager.createQuery("SELECT r FROM Role r").getResultList();
    }

    @Override
    public void addRole(Role role) {
        List<Role> roleSet = listRole();
        for (int i = 0; i < roleSet.size(); i++) {
            if (roleSet.get(i).getRole().equals(role.getRole())) {
            } else {
                manager.persist(role);
            }
        }
    }
    @Override
    public Role getRole(String role) {
        return manager.find(Role.class, role);
    }
}
