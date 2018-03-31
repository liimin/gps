package com.lj.gps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.lj.gps.biz.mapper")
public class GpsApplication {
	public static void main(String[] args) {
		SpringApplication.run(GpsApplication.class, args);
	}
}
