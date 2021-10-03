package com.example.springboot.service;

import com.example.springboot.dao.UserDao;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findUserByLogin(login);
        return User.fromUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(String name) {
        return userDao.getRole(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByName(String firstName) {
        return userDao.getByName(firstName);
    }
}
