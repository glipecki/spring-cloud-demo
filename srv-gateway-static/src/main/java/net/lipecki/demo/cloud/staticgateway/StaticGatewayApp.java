package net.lipecki.demo.cloud.staticgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class StaticGatewayApp {

	public static void main(String[] args) {
		SpringApplication.run(StaticGatewayApp.class, args);
	}

}