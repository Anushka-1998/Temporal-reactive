package com.clone.workflow.temporal.activity;

import com.clone.workflow.domain.RouteDTO;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Slf4j
public class ShippingActivityImpl implements ShippingActivity {

    private final ActivityCompletionClient activityCompletionClient;

    @Autowired
    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${restClient.routeService}")
    private String routeServiceUrl;

    public ShippingActivityImpl(ActivityCompletionClient activityCompletionClient) {
        this.activityCompletionClient = activityCompletionClient;
    }


   /* @Override
    public void getPossibleRoutes(String source, String destination) {
        var url = routeServiceUrl;

        log.info("Inside get routes method : {}",url);

        System.out.println(restTemplate.getForEntity(url, String.class));

         webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(RouteDTO.class)
                .log();

    }
*/

  /*  @Override
    public Flux<RouteDTO> getPossibleRoutes(String source, String destination) {
        var url = UriComponentsBuilder.fromHttpUrl(routeServiceUrl)
                .queryParam("source",source)
                .queryParam("destination",destination)
                .buildAndExpand().toUriString();

      *//*  HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);*//*
      //  ResponseEntity<RouteDTO> result = restTemplate.exchange(url, HttpMethod.GET, entity, RouteDTO.class);

        log.info("Inside get routes method : {}",url);
        Flux<RouteDTO> response = restTemplate.getForObject(url,Flux.class);
        log.info("result : {} ",response);

        return null;
    }*/

  //  private final WebClient webClient;

  /*  public MyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://example.org").build();
    }*/

    @Override
    public Flux<RouteDTO> getPossibleRoutes(String source, String destination) {

        ActivityExecutionContext context = Activity.getExecutionContext();

        byte[] taskToken = context.getTaskToken();
        ForkJoinPool.commonPool().execute(() -> getPossibleRoutesAsync(taskToken, source, destination));
        context.doNotCompleteOnReturn();

        return Flux.fromIterable(List.of(
                RouteDTO.builder().routeName("Gujarat").vesselSize(20.0).build(),
                RouteDTO.builder().routeName("Maharashtra").vesselSize(80.0).build(),
                RouteDTO.builder().routeName("Uttar Pradesh").vesselSize(40.0).build()));
    }


       /* Flux<RouteDTO> serverResponse =  webClient.get()
                .uri(url)
                .retrieve().bodyToFlux(RouteDTO.class).blockLast();*/
        //  log.info("Response from activity : {} ",serverResponse);
       /* return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(RouteDTO.class)
                .log();*/

        /*var response = Flux.fromIterable(List.of(
                RouteDTO.builder().routeName("Gujarat").vesselSize(20.0).build(),
                RouteDTO.builder().routeName("Maharashtra").vesselSize(80.0).build(),
                RouteDTO.builder().routeName("Uttar Pradesh").vesselSize(40.0).build()
        ));
        log.info("Flux RouteDTO : {}",response);*/

       // log.info("Inside get routes method : {}",url);

       /* Mono<ServerResponse> serverResponse =  webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ServerResponse.class)
                .log();*/
       /* serverResponse.flatMap(res->{
            res.ok().bodyValue(RouteDTO)
        })*/

      //  log.info("serverResponse : {}",serverResponse);



    public void  getPossibleRoutesAsync(byte[] taskToken, String source,String destination){

        log.info("getPossibleRoutesAsync  method called");
        var url = UriComponentsBuilder.fromHttpUrl(routeServiceUrl)
                .queryParam("source",source)
                .queryParam("destination",destination)
                .buildAndExpand().toUriString();
        log.info("URL : {}",url);

        Flux<RouteDTO> serverResponse =  webClient.get()
                .uri(url)
                .retrieve().bodyToFlux(RouteDTO.class).log();

        log.info("serverResponse : {}",serverResponse);
        activityCompletionClient.complete(taskToken, serverResponse);
    }


    @Override
    public Flux<String> getEquipmentAvailability(String containerType, int containerSize, int noOfContainers) {
        var url = "";
       /* return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(String.class)
                .log();*/
        return null;

    }

    @Override
    public Flux<String> spaceAvailability(Flux<RouteDTO> possibleRoutes, String noOfContainers) {
        var url = "";
      /*  return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(String.class)
                .log();*/
        return null;
    }
}



 /*   Flux<RouteDTO> serverResponse =  webClient.get()
            .uri(uriBuilder -> uriBuilder.path("possibleRoute")
                    .queryParam("source", "Rajasthan")
                    .queryParam("destination", "Karnataka")
                    .build())
            .retrieve().bodyToFlux(RouteDTO.class);
.log();*/