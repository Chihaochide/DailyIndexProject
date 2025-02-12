package com.dailyindex.stock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描异常拦截器
 */
@Configuration
@ComponentScan("com.daily.stock.exception")
public class ExceptionConfig {
}