package com.itcinfotech.zicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
public class ZicosBootApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ZicosBootApplication.class, args);
		/*ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ZicosBootApplication.class)
		.properties("spring.config.name:application,application_data")
		.build().run(args);
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		 
		System.out.println(environment.getProperty("taas.mail.from"));*/
		//SpringApplication.run(ZicosBootApplication.class, args);
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    MappingJackson2HttpMessageConverter converter = 
	        new MappingJackson2HttpMessageConverter(mapper);
	    return converter;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
