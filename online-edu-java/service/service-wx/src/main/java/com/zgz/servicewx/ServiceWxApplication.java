package com.zgz.servicewx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.xml.ws.Service;

/**
 * @author willie
 * @date 2021/12/3
 */
@ComponentScan({"com.zgz"})
@SpringBootApplication
@MapperScan("com.zgz.servicewx.mapper")
public class ServiceWxApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceWxApplication.class);
    }
}
