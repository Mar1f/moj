package com.mar.mojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.mar.mojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan(basePackages = {"com.mar"})
@EnableDiscoveryClient // 开启服务发现
@EnableFeignClients(basePackages = {"com.mar.mojbackendserviceclient.service"})
public class MojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MojBackendUserServiceApplication.class, args);
    }

}
