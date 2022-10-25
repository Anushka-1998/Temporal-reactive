package com.clone.workflow.temporal.workflow;

import com.clone.workflow.domain.Od3cpRequestInfo;
import com.clone.workflow.domain.ProductDetails;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import reactor.core.publisher.Mono;

@WorkflowInterface
public interface ShippingWorkFlow {

	public static final String QUEUE_NAME = "SHIPPING_QUEUE";

	@WorkflowMethod
	Mono<ProductDetails> getProductWorkflow(Od3cpRequestInfo requestInfo);


}
