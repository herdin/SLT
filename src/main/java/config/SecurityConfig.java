package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import service.TestLoginService;
import service.TestPasswordEncoderService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private TestLoginService testLoginService;
	@Autowired
	private TestPasswordEncoderService testPasswordEncoderService;
	
	public enum ROLE {
		USER("USER"),
		;
		private final String prefix = "ROLE_";
		private String role;
		ROLE(String role) { this.role = role; }
		public String getRoleString() { return this.role; }
		public String getAuthString() { return this.prefix + this.role; }
		@Override
		public String toString() { throw new RuntimeException("DO NOT USE"); }
	}
	
	public static class AUTH {
		public enum PERMIT_ALL {
			LOGIN_FORM    ("/auth/loginForm"),
			LOGIN_PROCESS ("/auth/login"),
			LOGOUT        ("/auth/logout"),
			RESOURCES     ("/resources/**"),
			ERROR404     ("/error404"),
			NOAUTH        ("/noauth/**"),
			;
			private String path;
			PERMIT_ALL(String path) { this.path = path; }
			public String getPathString() { return this.path; }
			@Override
			public String toString() { throw new RuntimeException("DO NOT USE"); }
		}
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		this.logger.info("");
		for(AUTH.PERMIT_ALL permitAll : AUTH.PERMIT_ALL.values()) {
			http.authorizeRequests().antMatchers(permitAll.getPathString()).permitAll();
		}
		
		http.authorizeRequests()
			.antMatchers("/**").hasRole(ROLE.USER.getRoleString())
			.anyRequest().authenticated();
//			.and()
		http
		.formLogin()
			.loginPage("/auth/loginForm")
			.usernameParameter("id")
			.passwordParameter("password")
			.loginProcessingUrl("/auth/login")
			.defaultSuccessUrl("/auth/main")
			.failureUrl("/auth/loginForm?error")
			.permitAll();
//			.and()
		http
		.logout()
			.logoutUrl("/auth/logout")
			.logoutSuccessUrl("/auth/loginForm?logout")
			.invalidateHttpSession(true);
		http
		.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		this.logger.info("");
		auth.userDetailsService(this.testLoginService).passwordEncoder(this.testPasswordEncoderService);
	}
}
