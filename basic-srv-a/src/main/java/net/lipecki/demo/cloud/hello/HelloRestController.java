package net.lipecki.demo.cloud.hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloRestController {

	@RequestMapping(value = "/{guy}", method = RequestMethod.GET)
	public Greeting helloGet(@PathVariable final String guy) {
		return Greeting.builder().message("Hi!").guyToIgnore(guy).build();
	}

}
