package com.example.springboot.dao;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void delete(long id);
    void editUser(User user);
    User getUserById(Long id);
    List<User> listUsers();
    User findUserByLogin(String login);
    Role getRole(String name);
    List<Role> getAllRoles();
    User getByEmail(String email);
    User getByName(String firstName);
}
