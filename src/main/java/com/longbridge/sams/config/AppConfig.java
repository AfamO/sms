package com.longbridge.sams.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.longbridge.sams.repository.CommonRepositoryImpl;

@Configuration
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableJpaRepositories(repositoryBaseClass = CommonRepositoryImpl.class,basePackages= {"com.longbridge.sams.repository"})
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class AppConfig {

	

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		String[] baseNames= new String[]{"messages"};
		source.setBasenames(baseNames);
		source.setCacheSeconds(60*30);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	
	 @Bean
	    public PasswordEncoder passwordEncoder(){
	        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        return passwordEncoder;
	    }
	 
	
}
