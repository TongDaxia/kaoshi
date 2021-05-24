package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kaoshi.tyg.mapper")
public class App {
    private static final Log LOGGER = LogFactory.getLog(App.class);

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
        LOGGER.info("App stared！！");
    }

}