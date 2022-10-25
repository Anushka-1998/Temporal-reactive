package com.clone.workflow.temporal.activity;

import com.clone.workflow.domain.RouteDTO;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@ActivityInterface
public interface ShippingActivity {

    @ActivityMethod
    Flux<RouteDTO> getPossibleRoutes(String source, String destination);

    @ActivityMethod
    Flux<String> getEquipmentAvailability(String containerType, int containerSize, int noOfContainers);

    @ActivityMethod
    Flux<String> spaceAvailability(Flux<RouteDTO> possibleRoutes, String noOfContainers);


}