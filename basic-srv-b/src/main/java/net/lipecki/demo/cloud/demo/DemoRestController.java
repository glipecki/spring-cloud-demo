package net.lipecki.demo.cloud.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoRestController {

	@RequestMapping("")
	public String demo() {
		return "ok";
	}

}
