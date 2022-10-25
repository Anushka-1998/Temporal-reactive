package com.clone.workflow.controller;

import com.clone.workflow.domain.Od3cpRequestInfo;
import com.clone.workflow.domain.ProductDetails;
import com.clone.workflow.domain.RouteDTO;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clone.workflow.service.ShippingService;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
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


	@Value("${restClient.routeService}")
	private String routeServiceUrl;

	@PostMapping("/startWorkflow")
	public Mono<ProductDetails> getProduct(@RequestBody Od3cpRequestInfo requestInfo) throws ExecutionException, InterruptedException {
		/*var url = UriComponentsBuilder.fromHttpUrl(routeServiceUrl)
				.queryParam("source",requestInfo.getSource())
				.queryParam("destination",requestInfo.getDestination())
				.buildAndExpand().toUriString();
		log.info("URL : {}",url);

		Flux<RouteDTO> serverResponse =  webClient.get()
				.uri(url)
				.retrieve().bodyToFlux(RouteDTO.class).log();
		log.info("Response from activity : {} ",serverResponse);*/


		String requestId = UUID.randomUUID().toString();
		requestInfo.setRequestId(requestId);
		log.info("Request Details : {}",requestInfo);
		return shippingService.getProductDetails(requestInfo);

	}

}
