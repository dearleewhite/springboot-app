package com.mercy;

import com.mercy.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: myapp
 * @Description:
 * @Author: xxx
 * @Date: 2020-12-15 15:09
 **/

@SpringBootApplication
@ComponentScan(basePackages = {"com.mercy.*"})
/**扫描mapper**/
@MapperScan(basePackageClasses = {UserMapper.class})
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class,args);
    }
}
