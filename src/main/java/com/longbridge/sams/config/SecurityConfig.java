package com.longbridge.sams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.security.config.CustomAuthenticationFilter;
import com.longbridge.sams.security.config.SchoolAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug=false)
public class SecurityConfig {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder encoder;

	@Configuration
	@Order(1)
	public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
		
		public SimpleUrlAuthenticationFailureHandler failureHandler() {
			return new SimpleUrlAuthenticationFailureHandler("/admin/login?error=true");
		}
		
		public SimpleUrlAuthenticationSuccessHandler successHandler() {
			return new SimpleUrlAuthenticationSuccessHandler("/admin/index");
		}
		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
			filter.setAuthenticationManager(authenticationManagerBean());
			filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/login", "POST"));
			filter.setAuthenticationSuccessHandler(successHandler());
			filter.setAuthenticationFailureHandler(failureHandler());
			
			http.authorizeRequests()
					.antMatchers("/css/**", "/img/**", "/ajax/**","/font-awesome/**", "/fonts/**", "/js/**", "/datatables/**")
					.permitAll().and()
					.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
					.antMatcher("/admin/**")
					.authorizeRequests().anyRequest()
					.access("hasRole('ROLE_ADMIN')").and()
					.formLogin().loginPage("/admin/login").permitAll()
					.defaultSuccessUrl("/admin/index").and()
					.logout().permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
					.logoutSuccessUrl("/admin/login?logout=true").invalidateHttpSession(true).and().csrf().disable();
		}
	}

	@Configuration
	@Order(2)
	public static class SchoolSecurityConfig extends WebSecurityConfigurerAdapter {
		
		public SimpleUrlAuthenticationFailureHandler failureHandler() {
			return new SimpleUrlAuthenticationFailureHandler("/school/login?error=true");
		}
		
		public SimpleUrlAuthenticationSuccessHandler successHandler() {
			return new SimpleUrlAuthenticationSuccessHandler("/school/index");
		}
		
		
		@Autowired
		private SchoolService schoolService;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
			filter.setAuthenticationManager(authenticationManagerBean());
			filter.setRequiresAuthenticationRequestMatcher( new RegexRequestMatcher("/school/login.*", "POST"));
			filter.setAuthenticationSuccessHandler(successHandler());
			filter.setAuthenticationFailureHandler(failureHandler());
			
			SchoolAuthenticationFilter filter2 = new SchoolAuthenticationFilter(schoolService);
			
			http.authorizeRequests()
					.antMatchers("/css/**", "/img/**", "/ajax/**", "/font-awesome/**", "/fonts/**", "/js/**", "/datatables/**").permitAll()
					.and().antMatcher("/school/**")
					.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
					.addFilterBefore(filter2, CustomAuthenticationFilter.class)
					.authorizeRequests().anyRequest()
					.access("hasRole('ROLE_STAFF')")
					.regexMatchers(HttpMethod.GET,"/school/login?.+").permitAll()
					.and()
					.formLogin().loginPage("/school/login").permitAll()
					.defaultSuccessUrl("/school/index").and()
					.logout().permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/school/logout"))
					.logoutSuccessUrl("/school/login?logout=true").invalidateHttpSession(true).and().csrf().disable();
	
		}
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(encoder);
		auth.authenticationProvider(provider);
		auth.userDetailsService(userDetailsService);
	}

}