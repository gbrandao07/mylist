package br.com.mylist.doingservice.services.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.mylist.doingservice.dao.entities.Card;

@FeignClient(value = "todo-service", url = "${todo-service.endpoints.base-path}")
public interface TodoServiceFacade {

	@PostMapping("${todo-service.endpoints.receive-card}")
	ResponseEntity<String> receiveCard(@RequestHeader String from, @RequestBody Card card);
}
