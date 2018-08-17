package com.longbridge.sams.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.longbridge.sams.security.config.CustomAuthenticationFilter;
import com.longbridge.sams.security.config.TypeUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(encoder);
		auth.authenticationProvider(provider);
		auth.userDetailsService(userDetailsService);
		//changes
//		super.configure(auth);
	}


	@Bean
	public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
		UsernamePasswordAuthenticationFilter filter = new TypeUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(failureHandler());
		return filter;
	}
	
	
	
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(failureHandler());
		filter.setAuthenticationSuccessHandler(successHandler());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/**", "POST"));
		return filter;
	}


	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/admin/login?error=true");
	}
	
	public SimpleUrlAuthenticationSuccessHandler successHandler() {
		return new SimpleUrlAuthenticationSuccessHandler("/admin/index");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/img/**", "/ajax/**", "/fonts/**", "/js/**","/datatables/**").permitAll().and()
				.antMatcher("/admin/**").addFilterBefore(customAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().anyRequest()
				.fullyAuthenticated().and()
//				.access("hasRole('ROLE_ADMIN')").and()
				.formLogin().permitAll().loginPage("/admin/login").loginProcessingUrl("/admin/login").and().logout()
				.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
				.logoutSuccessUrl("/admin/login?logout=true").invalidateHttpSession(true);
	}
}