package com.armstech.easycommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import com.armstech.easycommerce.config.CustomizationBean;
import com.armstech.easycommerce.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties({ ApiProperty.class })
public class SysjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysjobApplication.class, args);
	}

}
