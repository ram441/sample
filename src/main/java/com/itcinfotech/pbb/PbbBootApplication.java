package com.itcinfotech.pbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


//@RestController
@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
//@EnableResourceServer
public class PbbBootApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PbbBootApplication.class, args);
	}
	
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(PbbBootApplication.class);
	    }

}
