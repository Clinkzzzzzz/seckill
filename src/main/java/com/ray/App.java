package com.ray;

import com.ray.dao.UserDOMapper;
import com.ray.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages ={"com.ray"})
@RestController
@MapperScan("com.ray.dao")
public class App 
{
    @Autowired
    private UserDOMapper userDOMapper;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
    }
}
