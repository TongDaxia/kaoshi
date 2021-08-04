package com.kaoshi.tyg.common.实践;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class 读取配置 {


    public static void main(String[] args) throws IOException {

        Resource resource= new ClassPathResource("xxx.xml");

        InputStream inputStream = resource.getInputStream();


    }
}
