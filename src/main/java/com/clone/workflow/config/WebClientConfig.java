package com.clone.workflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(WebClient.Builder builder)
    {
        return builder.baseUrl("http://localhost:8084/").build();
      //  this.webClient = webClientBuilder.baseUrl("http://example.org").build();
      //  return builder.build();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

}

