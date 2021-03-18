package com.mz.aclasser;

import com.mz.aclasser.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AClasserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AClasserApplication.class, args);
    }
}

