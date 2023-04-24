package com.yty.spring6.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Configuration
@ComponentScan({"com.yty.spring6.service", "com.yty.spring6.aspect", "com.yty.spring6.biz"})
@EnableAspectJAutoProxy
public class Spring6Config {
}
