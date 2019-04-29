package com.callcenter.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Clase de configuración para hilos
 * @author loregaar
 *
 */
@Configuration
@EnableAsync
public class TaskExecutorConfig {

	@Bean(name = "dispatcherTaskExecutor")
	public TaskExecutor threadPoolTaskExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(20);
		executor.setThreadNamePrefix("dispatcher==>");
		executor.initialize();

		return executor;
	}

}
