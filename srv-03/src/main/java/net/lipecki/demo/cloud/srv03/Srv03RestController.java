package net.lipecki.demo.cloud.srv03;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@Slf4j
public class Srv03RestController {

	@RequestMapping("")
	@HystrixCommand(fallbackMethod = "sampleFallback")
	public String sample() {
		log.info("Something called me :)");
		return "Srv03";
	}

	public String sampleFallback() {
		return "hystrix command failed in srv03...";
	}

}
