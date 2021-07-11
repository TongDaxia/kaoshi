package com.kaoshi.tyg.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/account")
public class MiaoController {


    private Log log = LogFactory.getLog(MiaoController.class);


    @GetMapping("/login")
    public String login(ServletServerHttpRequest request) throws IOException {
        log.info("request:" + request.getBody().toString());
        return "haode";

    }

    @PostMapping("/")
    public String postAll() {
        return "haode";
    }


}
