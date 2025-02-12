package com.dailyindex.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.dailyindex.stock.mapper") // 扫描持久层的mapper接口，生成代理对象，并维护到spring的ioc容器中
@MapperScan(basePackages = {"com.daily.stock.mapper","com.dailyindex.stock.mapper"}) // 扫描持久层的mapper接口，生成代理对象，并维护到spring的ioc容器中
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class,args);
    }
}
