package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.Role;
import web.model.User;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(User user, String[] roles) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRoles(extractRoles(roles));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user, String[] roles) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRoles(extractRoles(roles));
        userDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    private Set<Role> extractRoles(String[] roles) {
        Set<Role> setRoles = new HashSet<>();
        for (String role : roles) {
            setRoles.add(new Role((role.equals("ADMIN") ? 2L : 1L), "ROLE_" + role));
        }
        return setRoles;
    }

}
