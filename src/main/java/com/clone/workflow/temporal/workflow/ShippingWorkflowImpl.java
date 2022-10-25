package com.clone.workflow.temporal.workflow;

import java.time.Duration;
import java.util.List;

import com.clone.workflow.domain.Od3cpRequestInfo;
import com.clone.workflow.domain.ProductDetails;
import com.clone.workflow.domain.RouteDTO;
import com.clone.workflow.domain.RouteDetails;
import com.clone.workflow.temporal.activity.ShippingActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


@Slf4j
public class ShippingWorkflowImpl implements ShippingWorkFlow {

	private final RetryOptions retryoptions = RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1))
			.setMaximumInterval(Duration.ofSeconds(100)).setBackoffCoefficient(2).setMaximumAttempts(1).build();

	private final ActivityOptions option = ActivityOptions.newBuilder()
	.setStartToCloseTimeout(Duration.ofSeconds(40))
			.setTaskQueue(ShippingWorkFlow.QUEUE_NAME)
			.setRetryOptions(retryoptions).build();


	private ShippingActivity shippingActivity = Workflow.newActivityStub(ShippingActivity.class, option);



//	@Override
//	public Mono<ProductDetails> getProductWorkflow(Od3cpRequestInfo requestInfo) {
//		log.info("--Workflow start here--");
//	//	Promise<Flux<RouteDTO>> possibleRoutes  = Async.function(shippingActivity::getPossibleRoutes, requestInfo.getSource(), requestInfo.getDestination());
//	//	Promise<Flux<String>> equipmentAvailability = Async.function(shippingActivity::getEquipmentAvailability,requestInfo.getContainerType(),requestInfo.getContainerSize(),requestInfo.getNoOfContainers());
//		Flux<RouteDTO> possibleRoutes = shippingActivity.getPossibleRoutes(requestInfo.getSource(), requestInfo.getDestination());
//		return possibleRoutes.doOnNext(res-> log.info("Response : {}",res))
//				.collectList()
//				.map(this::getRouteDetails);
//	}
//
//
//	public ProductDetails getRouteDetails(List<RouteDTO> routesList){
//		log.info("routesList : {}",routesList);
//		return ProductDetails.builder().availableRoutes(routesList).build();
//
//	}



	//	private final CheckAvailabilityActivity checkAvailabilityActivity = Workflow.newActivityStub(CheckAvailabilityActivity.class, option);

	@Override
	public Mono<ProductDetails> getProductWorkflow(Od3cpRequestInfo requestInfo) {

		log.info("--Workflow start here--");

		Flux<RouteDTO> possibleRoutes = shippingActivity
				.getPossibleRoutes(requestInfo.getSource(), requestInfo.getDestination());


		log.info("possibleRoutes: {}",possibleRoutes);
		return Mono.just(ProductDetails.builder()
					.productId("").ContainerSize(0.0).containerType("").build());
	}
}
