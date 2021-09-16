package com.liao.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LiaoModulesSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiaoModulesSystemApplication.class, args);
	}

}
