package com.aurosoft.restapi.runner;


import com.aurosoft.restapi.service.RoleService;
import com.aurosoft.restapi.service.UserService;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    @Override
    public void run(String... args) throws Exception {
        createRoles();
        craeteAdmin();

    }

    private void craeteAdmin() {
    }

    private void createRoles() {
        Arrays.asList("Admin","Instructor","Student").forEach(role -> roleService.createRole(role));
    }
}
