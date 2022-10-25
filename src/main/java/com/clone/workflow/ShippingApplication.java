package com.clone.workflow;

import com.clone.workflow.temporal.activity.ShippingActivity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.clone.workflow.temporal.workflow.ShippingWorkFlow;
import com.clone.workflow.temporal.workflow.ShippingWorkflowImpl;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

@SpringBootApplication
public class ShippingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(ShippingApplication.class, args);
		WorkerFactory factory = appContext.getBean(WorkerFactory.class);

		ShippingActivity shippingActivity = appContext.getBean(ShippingActivity.class);
		Worker worker = factory.newWorker(ShippingWorkFlow.QUEUE_NAME);
		worker.registerWorkflowImplementationTypes(ShippingWorkflowImpl.class);
		worker.registerActivitiesImplementations(shippingActivity);
		factory.start();
	}

}
