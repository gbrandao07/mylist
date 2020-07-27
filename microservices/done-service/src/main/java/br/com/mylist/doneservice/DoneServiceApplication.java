package br.com.mylist.doneservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DoneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoneServiceApplication.class, args);
	}

}
