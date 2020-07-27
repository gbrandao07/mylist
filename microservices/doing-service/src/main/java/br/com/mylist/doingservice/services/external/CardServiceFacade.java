package br.com.mylist.doingservice.services.external;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "card-service", url = "${card-service.endpoints.base-path}")
public interface CardServiceFacade {
	
	@PutMapping("${card-service.endpoints.update-card}")
	Long updateCard(@RequestParam Map<String,String> params);
}	
