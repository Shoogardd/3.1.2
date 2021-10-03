package com.example.springboot.service;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import javax.transaction.Transactional;
import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);
    void delete(long id);
    void editUser(User user);
    User getUserById(Long id);
    List<User> listUsers();;
    User findUserByLogin(String login);
    Role getRole(String name);
    List<Role> getAllRoles();
    User getByEmail(String email);
    User getByName(String firstName);
}
