package com.aurosoft.restapi.web;

import com.aurosoft.restapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UseRestController {
private UserService userService;

    public UseRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("users")
    public boolean checkIfEmailExists(@RequestParam(name = "email", defaultValue = "")String email)
    {
       return userService.loadUserByEmail(email) !=null;


    }
}
