package com.dailyindex.stock.config;

import com.daily.stock.utils.BCrypt;
import com.daily.stock.utils.IdWorker;
import com.daily.stock.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义公共配置bean
 */
@Configuration
public class CommonConfig {

    @Bean
    public IdWorker idWorker(){
        /**
         * 参数1：机器ID
         * 参数2：机房ID
         * 机器和机器编号一遍由运维人员进行唯一性规划
         */
        return new IdWorker(1l,2l);
    }
}
