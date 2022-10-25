package com.clone.workflow.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.util.RouteMatcher;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails implements Serializable {
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("availableRoutes")
    private List<RouteDTO> availableRoutes;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("containerType")
    private String containerType;
    @JsonProperty("ContainerSize")
    private Double ContainerSize;

}
