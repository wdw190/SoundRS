package com.sound.rs.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sound.rs.demo.hello1.HelloServiceImpl1;
import com.sound.rs.demo.hello2.HelloServiceImpl2;
@Configuration
public class RSConfig {

	 @Autowired
	    private Bus bus;
	 
	 @Bean
	    public Server rsServer() {
	        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
	        endpoint.setBus(bus);
	        endpoint.setServiceBeans(Arrays.<Object>asList(new HelloServiceImpl1(), new HelloServiceImpl2()));
	        endpoint.setAddress("/");
	        endpoint.setFeatures(Arrays.asList(createSwaggerFeature(), new LoggingFeature()));
	        return endpoint.create();
	    }

	    public Swagger2Feature createSwaggerFeature() {
	        Swagger2Feature swagger2Feature = new Swagger2Feature();
	        swagger2Feature.setPrettyPrint(true);
	        swagger2Feature.setTitle("Spring Boot CXF REST Application");
	        swagger2Feature.setContact("The Apache CXF team");
	        swagger2Feature.setDescription("This sample project demonstrates how to use CXF JAX-RS services"
	                + " with Spring Boot. This demo has two JAX-RS class resources being"
	                + " deployed in a single JAX-RS endpoint.");
	        swagger2Feature.setVersion("1.0.0");
	        return swagger2Feature;
	    }
}
