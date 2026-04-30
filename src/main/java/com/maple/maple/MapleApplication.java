package com.maple.maple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.maple.maple.mapper")
@EnableAspectJAutoProxy(exposeProxy = true) // 开启AOP代理，暴露代理对象
public class MapleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapleApplication.class, args);
    }

}
