package net.lipecki.demo.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableZipkinStreamServer
public class ZipkinApp {

	public static void main(String... args) {
		SpringApplication.run(ZipkinApp.class, args);
	}

}
