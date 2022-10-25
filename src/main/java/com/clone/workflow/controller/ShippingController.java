package com.clone.workflow.controller;

import com.clone.workflow.domain.Od3cpRequestInfo;
import com.clone.workflow.domain.ProductDetails;
import com.clone.workflow.domain.RouteDTO;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clone.workflow.service.ShippingService;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class ShippingController {

	@Autowired
	WebClient webClient;

	@Autowired
	ShippingService shippingService;

	@PostMapping("/startWorkflow")
	public Mono<ProductDetails> getProduct(@RequestBody Od3cpRequestInfo requestInfo) throws ExecutionException, InterruptedException {
		/*Flux<RouteDTO> serverResponse =  webClient.get()
				.uri(uriBuilder -> uriBuilder.path("possibleRoute")
						.queryParam("source", "Rajasthan")
						.queryParam("destination", "Karnataka")
						.build())
				.retrieve().bodyToFlux(RouteDTO.class).log();
		log.info("Response : {} ",serverResponse);*/
	//	serverResponse.flatMap(res->serverResponse.collectList())

		String requestId = UUID.randomUUID().toString();
		requestInfo.setRequestId(requestId);
		log.info("Request Details : {}",requestInfo);
		return shippingService.getProductDetails(requestInfo);

	}

}
