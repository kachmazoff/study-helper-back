package com.kach.studyhelperback;

import com.kach.studyhelperback.security.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class StudyHelperBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyHelperBackApplication.class, args);
    }

}
