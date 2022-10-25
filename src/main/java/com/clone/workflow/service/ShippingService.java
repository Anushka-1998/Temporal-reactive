package com.clone.workflow.service;

import com.clone.workflow.domain.Od3cpRequestInfo;
import com.clone.workflow.domain.ProductDetails;
import io.temporal.api.common.v1.WorkflowExecution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clone.workflow.temporal.workflow.ShippingWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ShippingService {

	@Autowired
	WorkflowServiceStubs workflowServiceStubs;

	@Autowired
	WorkflowClient workflowClient;

	public Mono<ProductDetails> getProductDetails(Od3cpRequestInfo requestInfo) throws ExecutionException, InterruptedException {
		ShippingWorkFlow workflow = createWorkFlowConnection(requestInfo.getRequestId());
	//	CompletableFuture<Mono<ProductDetails>> productDetails = WorkflowClient.execute(workflow::getProductWorkflow,requestInfo);
		//return productDetails.get();
		WorkflowExecution productDetails = WorkflowClient.start(workflow::getProductWorkflow,requestInfo);
		return workflow.getProductWorkflow(requestInfo);

	}


	public ShippingWorkFlow createWorkFlowConnection(String requestId) {
		String workflowId = "Booking_Request_" + requestId;
		log.info("WorkflowId: {}",workflowId);
		WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(ShippingWorkFlow.QUEUE_NAME)
				.setWorkflowId("Booking_Request_" + requestId).build();
		return workflowClient.newWorkflowStub(ShippingWorkFlow.class, options);
	}

}
