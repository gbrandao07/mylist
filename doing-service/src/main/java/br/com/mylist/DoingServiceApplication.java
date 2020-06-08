package br.com.mylist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DoingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoingServiceApplication.class, args);
	}

}
