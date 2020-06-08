package br.com.mylist.services.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.mylist.dao.entities.Card;

@FeignClient(value = "done-service", url = "${done-service.endpoints.base-path}")
public interface DoneServiceFacade {

	@PostMapping("${done-service.endpoints.receive-card}")
	ResponseEntity<String> receiveCard(@RequestHeader String from, @RequestBody Card card);
}
