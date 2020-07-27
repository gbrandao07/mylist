package br.com.mylist.todoservice.services.external;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "card-service", url = "${card-service.endpoints.base-path}")
public interface CardServiceFacade {

	@PostMapping("${card-service.endpoints.create-card}")
	Long createCard(@RequestParam Map<String,String> params);
	
	@PutMapping("${card-service.endpoints.update-card}")
	Long updateCard(@RequestParam Map<String,String> params);
}	
