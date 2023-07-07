package com.aurosoft.restapi.service;

import com.aurosoft.restapi.entity.User;

public interface UserService {

    User loadUserByEmail(String email);

    User createUser(String email, String password);

    void assignRoleToUser(String email, String name);
}
