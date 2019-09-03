package com.oranfish.sushiweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.oranfish.sushiweb.filter")
@ComponentScan({"com.oranfish.sushidao","com.oranfish.sushiservice","com.oranfish.sushiweb"})
@MapperScan("com.oranfish.sushidao.mapper")
public class SushiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SushiWebApplication.class, args);
    }

}