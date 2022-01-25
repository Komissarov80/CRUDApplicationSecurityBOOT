package org.exampels.Services;

import org.exampels.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> listRole();
 //   void addRole(Role role);
    Role getRole(String name);
}
