package cc.akali.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: dan
 * @Date: 2020/4/29 9:03
 * @Description:
 */
@SpringBootApplication
//扫描所有的cc.akali包
@ComponentScan(basePackages ={"cc.akali"} )
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
