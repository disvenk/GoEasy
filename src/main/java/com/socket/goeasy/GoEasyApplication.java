package com.socket.goeasy;

import com.socket.goeasy.controller.DomainFilter;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GoEasyApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GoEasyApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GoEasyApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		DomainFilter domainFilter = new DomainFilter();
		registrationBean.setFilter(domainFilter);
//		List<String> urlPatterns = new ArrayList<String>();
//		urlPatterns.add("/*");
//		registrationBean.setUrlPatterns(urlPatterns);
//		registrationBean.addUrlPatterns();
//		registrationBean.addInitParameter("paramName", "paramValue");
//		registrationBean.setName("sessionFilter");
		return registrationBean;
	}

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
		return (factory) -> factory.addContextCustomizers(
				(context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
	}

}
