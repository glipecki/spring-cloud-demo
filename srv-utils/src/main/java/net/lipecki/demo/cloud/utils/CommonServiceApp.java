package net.lipecki.demo.cloud.utils;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Standard microservice configuration class.
 * <p>
 * Example usage:
 * <ul>
 * <li>extend this class</li>
 * <li>add main method from tempalte</li>
 * </ul>
 * Sample app main method template:
 * <pre>
 * public static void main(final String... args) {
 *   appBuilder(SampleServiceApp.class).run(args);
 * }
 * </pre>
 * </p>
 */
@SpringBootApplication
@EnableAsync
@EnableDiscoveryClient
@EnableCircuitBreaker
public abstract class CommonServiceApp {

	public static CommonServiceAppBuilder appBuilder(final Class<?> appClass) {
		return new CommonServiceAppBuilder(appClass);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@LoadBalanced
	@Bean
	public AsyncRestTemplate asyncRestTemplate() {
		return new AsyncRestTemplate();
	}

}
