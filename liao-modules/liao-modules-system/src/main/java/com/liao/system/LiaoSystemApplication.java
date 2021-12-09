package com.liao.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LiaoSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiaoSystemApplication.class, args);
	}

}
