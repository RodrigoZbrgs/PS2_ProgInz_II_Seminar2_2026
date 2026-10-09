package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsManager createDummyUsers() {
		PasswordEncoder encoder = 
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		
		UserDetails userD1 = 
				User.builder()
				.username("rodrigo")
				.password(encoder.encode("123"))
				.authorities("USER")
				.build();
		UserDetails userD2 = 
				User.builder()
				.username("rodrigoz")
				.password(encoder.encode("321"))
				.authorities("ADMIN")
				.build();
		
		InMemoryUserDetailsManager manager = 
				new InMemoryUserDetailsManager(userD1, userD2);
		return manager;
		
	}
	
	
	@Bean
	public SecurityFilterChain httpPermisions(HttpSecurity http) {
		http.authorizeHttpRequests(
				auth->auth
				.requestMatchers("/student/crud/all").permitAll()
				//.requestMatchers("/student/crud/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/student/crud/add").permitAll()
				.requestMatchers("/filter/**").hasAuthority("USER"));
		
		http.formLogin(auth->auth.permitAll());
		
		return http.build();
		
		
		
		
		
		
		
	}
	

}
