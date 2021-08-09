package com.zhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //开启定时功能注解
@SpringBootApplication
@MapperScan("com.zhang.mapper")
public class SpringbootMyatisPlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootMyatisPlusApplication.class, args);
  }

}
