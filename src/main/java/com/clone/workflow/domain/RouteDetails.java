package com.clone.workflow.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RouteDetails implements Serializable {
    
    @JsonProperty("availableRoutes")
    private List<String> availableRoutes;

}
