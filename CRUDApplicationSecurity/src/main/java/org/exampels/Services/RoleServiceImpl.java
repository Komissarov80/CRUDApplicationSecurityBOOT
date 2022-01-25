package org.exampels.Services;

import org.exampels.dao.DaoRole;
import org.exampels.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    DaoRole daoRole;

    @Override
    public List<Role> listRole() {
        List<Role> roleList = daoRole.listRole();
        return roleList;
    }

//    @Override
//    public void addRole(Role role) {
//         daoRole.addRole(role);
//    }

    @Override
    public Role getRole(String role) {
        Role theRole = daoRole.getRole(role);
        return theRole;
    }
}
