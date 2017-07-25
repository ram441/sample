/*package com.itcinfotech.zicos.security;

*//**
 * 
 *//*
import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import redis.embedded.RedisServer;

*//**
 * @author Vinod Kumar
 *
 *//*
@Configuration
@EnableRedisHttpSession
public class EmbeddedRedisConfiguration {
    private static RedisServer redisServer;
    private static JedisConnectionFactory connectionFactory=null;
    
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
    @Bean
    public JedisConnectionFactory connectionFactory() throws IOException, URISyntaxException {
    	redisServer = new RedisServer(Protocol.DEFAULT_PORT);
    	try{	
    		redisServer.start();
    	}catch(Exception e){
    		System.out.println("============================================");
    		e.printStackTrace();
    		System.out.println("============================================");
    	}
    	connectionFactory=new JedisConnectionFactory();
    	return connectionFactory;
    }
    
    @Bean
    public JedisConnectionFactory connectionFactory() {
    	
    	connectionFactory=new JedisConnectionFactory();
    	return connectionFactory;
    }
    @PreDestroy
    public void destroy() throws Exception {
       redisServer.stop();
       redisServer.start();
    }

}
*/