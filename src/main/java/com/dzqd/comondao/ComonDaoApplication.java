package com.dzqd.comondao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dzqd.comondao.dao")
public class ComonDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComonDaoApplication.class, args);
	}
}
