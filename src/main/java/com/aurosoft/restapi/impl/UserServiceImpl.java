package com.aurosoft.restapi.impl;

import com.aurosoft.restapi.dao.RoleDao;
import com.aurosoft.restapi.dao.UserDao;
import com.aurosoft.restapi.entity.Role;
import com.aurosoft.restapi.entity.User;
import com.aurosoft.restapi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        return userDao.save(new User(email, password));
    }

    @Override
    public void assignRoleToUser(String email, String name) {
    User user = loadUserByEmail(email);
    Role role = roleDao.findByName(name);
    user.assignRoleToUser(role);
    }
}
