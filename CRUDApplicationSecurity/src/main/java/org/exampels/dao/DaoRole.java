package org.exampels.dao;

import org.exampels.model.Role;

import java.util.List;

public interface DaoRole {
    List<Role> listRole();
    void addRole(Role role);
    Role getRole(String role);
}
