package cn.edu.fzu.sosd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis")
public class SosdApplication {

    public static void main(String[] args) {
        SpringApplication.run(SosdApplication.class, args);
    }
}
