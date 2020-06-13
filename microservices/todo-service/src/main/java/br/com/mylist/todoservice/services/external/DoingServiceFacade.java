package br.com.mylist.todoservice.services.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.mylist.todoservice.dao.entities.Card;

@FeignClient(value = "doing-service", url = "${doing-service.endpoints.base-path}")
public interface DoingServiceFacade {

	@PostMapping("${doing-service.endpoints.receive-card}")
	ResponseEntity<String> receiveCard(@RequestHeader String from, @RequestBody Card card);
}
