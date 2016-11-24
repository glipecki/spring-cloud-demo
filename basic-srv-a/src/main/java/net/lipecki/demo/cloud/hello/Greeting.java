package net.lipecki.demo.cloud.hello;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Greeting {

	private String message;

	private String guyToIgnore;

}
