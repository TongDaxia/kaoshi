package com.kaoshi.tyg.controller;


import com.kaoshi.tyg.common.CommonResponse;
import com.kaoshi.tyg.common.ReturnCode;
import com.kaoshi.tyg.entity.User;
import com.kaoshi.tyg.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/findAll")
    public CommonResponse<List<User>> queryAll() {


        return new CommonResponse<List<User>>(ReturnCode.SUCCESS, userMapper.findAll());
    }





}
