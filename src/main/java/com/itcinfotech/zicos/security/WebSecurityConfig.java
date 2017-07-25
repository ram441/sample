package com.itcinfotech.zicos.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.itcinfotech.zicos.core.CustomUserDetailsService;
import com.itcinfotech.zicos.core.SimpleLoginFilter;
import com.itcinfotech.zicos.sql.model.EndUser;
import com.itcinfotech.zicos.sql.model.Role;
import com.itcinfotech.zicos.sql.repository.EndUserRepository;
import com.itcinfotech.zicos.sql.repository.GuestUserRepository;
import com.itcinfotech.zicos.sql.repository.RoleRepository;

import static com.itcinfotech.zicos.sql.model.EndUser.*;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EndUserRepository endUserRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/")
				.permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/console/**")
				.permitAll()
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/signup/**", "/signup", "/about", "login",
						"login?error").permitAll().and().authorizeRequests()
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN").anyRequest()
				.authenticated();

		http.sessionManagement().maximumSessions(1000)
				.sessionRegistry(sessionRegistry()).and().sessionFixation()
				.none();
		/*
		 * .authorizeRequests().antMatchers(HttpMethod.GET,"/user","/user/**").
		 * hasAnyAuthority("SUPPORT","ADMIN","USER").and()
		 * .authorizeRequests().antMatchers
		 * (HttpMethod.GET,"/customer").hasAnyAuthority("SUPPORT","ADMIN").and()
		 * .authorizeRequests().antMatchers(HttpMethod.GET,"/customer/**").
		 * hasAnyAuthority("SUPPORT","ADMIN","USER").and()
		 * .authorizeRequests().antMatchers
		 * (HttpMethod.GET,"/logedincustomer").hasAnyAuthority
		 * ("SUPPORT","ADMIN","USER").and()
		 * .authorizeRequests().antMatchers(HttpMethod
		 * .GET,"/heater/**").hasAnyAuthority("SUPPORT","ADMIN","USER").and()
		 * .authorizeRequests
		 * ().antMatchers(HttpMethod.GET,"/average_reading/**")
		 * .hasAnyAuthority("SUPPORT","ADMIN","USER").and()
		 * .authorizeRequests().
		 * antMatchers(HttpMethod.PUT,"/passwordchange").hasAnyAuthority
		 * ("SUPPORT","ADMIN","USER").and()
		 * .authorizeRequests().antMatchers(HttpMethod
		 * .GET,"/national_average/**"
		 * ).hasAnyAuthority("SUPPORT","ADMIN","USER")
		 * .anyRequest().authenticated();
		 */

		http.logout().logoutUrl("/logout").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessHandler(new LogoutSuccessHandler() {

					@Override
					public void onLogoutSuccess(HttpServletRequest request,
							HttpServletResponse response, Authentication auth)
							throws IOException, ServletException {
						String sessionId = request.getHeader("x-auth-token");
						sessionRegistry.removeSessionInformation(sessionId);
						response.setStatus(200);
					}

				});

		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.requestCache().requestCache(new NullRequestCache());
		http.httpBasic();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.userDetailsService(customUserDetailsService).passwordEncoder(
				bCryptPasswordEncoder);

		if (endUserRepository.count() < 1) {
			Role role = roleRepository.getOne(1);

			EndUser user = new UserBuilder().displayName("super_user")
					.password(bCryptPasswordEncoder.encode("password"))
					.email("super_user@demo.com").firstName("admin")
					.lastName("lastName").contactNumber("1234567890")
					.creadtedBy("super_user@demo.com").creadtedDate(new Date())
					.status(false).contactMobile("1234567890").role(role)
					.build();

			user = endUserRepository.save(user);
		}

	}

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public FilterRegistrationBean corsFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("corsFilter");
		SimpleLoginFilter loginFilter = new SimpleLoginFilter();
		registrationBean.setFilter(loginFilter);
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}
}
