package com.callcenter.demo.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Clase de configuración para archivos de propiedades
 * @author Lorena Garcia Arias
 *
 */
@Configuration
public class PropertiesConfig {

	@Bean(name = "database")
	public PropertiesFactoryBean mapper() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("database.properties"));
		return bean;
	}

	@Bean(name = "messages")
	public PropertiesFactoryBean mapperMessages() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("messages.properties"));
		return bean;
	}

}
