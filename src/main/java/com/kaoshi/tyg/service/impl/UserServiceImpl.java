package com.kaoshi.tyg.service.impl;

import com.kaoshi.tyg.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, CommandLineRunner {


    @Bean
    public String serviceName() {
        return "MyServiceName";
    }


    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
