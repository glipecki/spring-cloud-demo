package net.lipecki.demo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicSrvAApp {

	public static void main(String... args) {
		new SpringApplication(BasicSrvAApp.class).run(args);
	}

}
