package com.nishiProductions.TestApplication.controller;

import com.nishiProductions.TestApplication.repository.dto.ResponseDto;
import com.nishiProductions.TestApplication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("user")
@CrossOrigin()
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("allUsers")
    public ResponseDto getAllusers() {
        log.info("UserController.getAllusers invoked");
        return userService.getAllUsers();
    }

}
