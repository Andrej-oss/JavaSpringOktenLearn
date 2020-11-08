package org.okten.javaadvanced;

import org.okten.javaadvanced.config.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class JavaAdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAdvancedApplication.class, args);
	}

}
