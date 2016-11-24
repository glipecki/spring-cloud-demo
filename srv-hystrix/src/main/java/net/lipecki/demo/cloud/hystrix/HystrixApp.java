package net.lipecki.demo.cloud.hystrix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.cloud.netflix.turbine.stream.TurbineStreamProperties;
import org.springframework.util.SocketUtils;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
@Slf4j
public class HystrixApp {

	public static void main(String... args) {
		SpringApplication.run(HystrixApp.class, args);
	}

	@Autowired
	private TurbineStreamProperties turbineStreamProperties;

	@PostConstruct
	public void showTurbinePort() {
		if (turbineStreamProperties.getPort() <= 0) {
			int turbinePort = SocketUtils.findAvailableTcpPort(40000);
			turbineStreamProperties.setPort(turbinePort);
		}
		log.info("Turbine port: {}", turbineStreamProperties.getPort());
	}

}
