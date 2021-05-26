package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.kaoshi.tyg.mapper")
//@EnableAspectJAutoProxy
@EnableTransactionManagement
public class App {
    private static final Log LOGGER = LogFactory.getLog(App.class);

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
        LOGGER.info("--------------------server stared！！--------------------------");
    }

}