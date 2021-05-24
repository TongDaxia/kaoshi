package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.kaoshi.tyg.mapper")
public class App {
    private static final Log LOGGER = LogFactory.getLog(App.class);

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
        LOGGER.info("--------------------server stared！！--------------------------");
    }

}