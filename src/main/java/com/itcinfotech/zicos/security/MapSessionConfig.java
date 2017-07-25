package com.itcinfotech.zicos.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@Configuration
@EnableSpringHttpSession
// @ConditionalOnProperty(name = "use.redis.session.store", havingValue =
// "false", matchIfMissing = false)
public class MapSessionConfig {

	@Value("${server.session.timeout}")
	private int inactiveInterval;

	@Bean
	public SessionRepository<ExpiringSession> sessionRepository() {
		MapSessionRepository mapSessionRepository = new MapSessionRepository();
		mapSessionRepository.setDefaultMaxInactiveInterval(inactiveInterval);
		return mapSessionRepository;
	}

}
