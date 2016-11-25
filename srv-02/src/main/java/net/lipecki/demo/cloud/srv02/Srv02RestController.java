package net.lipecki.demo.cloud.srv02;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/")
@RestController
@Slf4j
public class Srv02RestController {

	private final RestTemplate restTemplate;

	public Srv02RestController(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@RequestMapping("")
	public String sample() {
		log.info("Something called me :)");
		return "Srv02";
	}

	@RequestMapping("look-for-friends")
	@HystrixCommand(fallbackMethod = "sampleFallback")
	public String lookForFriends() {
		log.info("Something called me :)");
		return "I'm Srv02 and i see: " + restTemplate.getForObject("http://srv03/", String.class);
	}

	public String sampleFallback() {
		return "hystrix command failed in srv02...";
	}

}
