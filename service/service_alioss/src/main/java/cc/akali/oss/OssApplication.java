package cc.akali.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: dan
 * @Date: 2020/5/14 15:08
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//不配置数据库
//扫描所有的cc.akali包
@ComponentScan(basePackages ={"cc.akali"} )
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}