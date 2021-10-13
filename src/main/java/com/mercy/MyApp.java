package com.mercy;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.mercy.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author: myapp
 * @Description:
 * @Author: xxx
 * @Date: 2020-12-15 15:09
 **/

@SpringBootApplication
@ComponentScan(basePackages = {"com.mercy.*"})
@MapperScan(basePackageClasses = {UserMapper.class})
public class MyApp implements CommandLineRunner{

    @NacosInjected
    private NamingService namingService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @Override
    public void run(String... args) throws Exception {
        // 通过Naming服务注册实例到注册中心
        InetAddress address = InetAddress.getLocalHost();
        namingService.registerInstance(applicationName, address.toString(), serverPort);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class,args);
    }
}
