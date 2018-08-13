//package com.longbridge.sams.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import com.longbridge.sams.security.config.SchoolAuthenticationFilter;
//import com.longbridge.sams.security.config.TypeUsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//public class SpringSecurity {
//
//
//	
//	@Configuration
//	@Order(1)
//	public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {
//		@Autowired
//		@Qualifier("userDetailsService")
//		private UserDetailsService userDetailsService;
//
//		@Autowired
//		public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder encoder) throws Exception {
//			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//			provider.setUserDetailsService(userDetailsService);
//			provider.setPasswordEncoder(encoder);
//			auth.authenticationProvider(provider);
//		}
//
//		 
//		  
//		 
//		@Bean
//		public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
//			UsernamePasswordAuthenticationFilter filter = new TypeUsernamePasswordAuthenticationFilter();
//	        filter.setAuthenticationManager(authenticationManagerBean());
//	        filter.setAuthenticationFailureHandler(failureHandler());
//	        return filter;
//	    }
//		
//		 public SimpleUrlAuthenticationFailureHandler failureHandler() {
//		        return new SimpleUrlAuthenticationFailureHandler("/admin/login?error=true");
//		  }
//		 
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class).authorizeRequests()
//					.antMatchers("/css/**", "/img/**", "/ajax/**", "/fonts/**", "/js/**").permitAll().and()
//					.antMatcher("/admin/**").authorizeRequests().anyRequest().access("hasRole('ROLE_ADMIN')").and()
//					.formLogin().permitAll().loginPage("/admin/login")
//					.defaultSuccessUrl("/admin/index").and().logout().permitAll()
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//					.logoutSuccessUrl("/admin/login?logout=true").invalidateHttpSession(true);
//		}
//	}
//
//	@Configuration
//	@Order(2)
//	public static class SchoolConfigurationAdapter extends WebSecurityConfigurerAdapter {
//		@Autowired
//		@Qualifier("userDetailsService")
//		private UserDetailsService userDetailsService;
//
//		@Autowired
//		public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder encoder) throws Exception {
//			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//			provider.setUserDetailsService(userDetailsService);
//			provider.setPasswordEncoder(encoder);
//			auth.authenticationProvider(provider);
//		}
//
//		
//		public TypeUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
//			TypeUsernamePasswordAuthenticationFilter filter = new TypeUsernamePasswordAuthenticationFilter();
//	        filter.setAuthenticationManager(authenticationManagerBean());
//	        filter.setAuthenticationFailureHandler(failureHandler());
//	        return filter;
//	    }
//		
//		 public SimpleUrlAuthenticationFailureHandler failureHandler() {
//		        return new SimpleUrlAuthenticationFailureHandler("/school/login?error=true");
//		  }
//		 
//		 
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.addFilterBefore(new SchoolAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//					.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class).authorizeRequests()
//					.antMatchers("/css/**", "/img/**", "/ajax/**", "/fonts/**", "/js/**").permitAll().and()
//					.antMatcher("/school/**").authorizeRequests().anyRequest().access("hasRole('ROLE_SCHOOL')").and()
//					.formLogin().permitAll().loginPage("/school/login")
//					.defaultSuccessUrl("/school/index").and().logout().permitAll()
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//					.logoutSuccessUrl("/school/login?logout=true").invalidateHttpSession(true);
//		}
//	}
//
//	@Configuration
//	@Order(3)
//	public static class PublicConfigurationAdapter extends WebSecurityConfigurerAdapter {
//		
//		@Autowired
//		@Qualifier("userDetailsService")
//		private UserDetailsService userDetailsService;
//
//		@Autowired
//		public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder encoder) throws Exception {
//			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//			provider.setUserDetailsService(userDetailsService);
//			provider.setPasswordEncoder(encoder);
//			auth.authenticationProvider(provider);
//		}
//
//		public TypeUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
//			TypeUsernamePasswordAuthenticationFilter filter = new TypeUsernamePasswordAuthenticationFilter();
//	        filter.setAuthenticationManager(authenticationManagerBean());
//	        filter.setAuthenticationFailureHandler(failureHandler());
//	        return filter;
//	    }
//		
//		 public SimpleUrlAuthenticationFailureHandler failureHandler() {
//		        return new SimpleUrlAuthenticationFailureHandler("/pub/login?error=true");
//		  }
//		 
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.addFilterBefore(new SchoolAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//					.addFilterBefore(authenticationFilter(),
//							UsernamePasswordAuthenticationFilter.class)
//					.authorizeRequests().antMatchers("/css/**", "/img/**", "/ajax/**", "/fonts/**", "/js/**")
//					.permitAll().and().authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
//					.loginPage("/pub/login").defaultSuccessUrl("/pub/index").and()
//					.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//					.logoutSuccessUrl("/pub/login?logout=true").invalidateHttpSession(true);
//		}
//	}
//
//}
