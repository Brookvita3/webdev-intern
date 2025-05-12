package com.example.webdev_intern.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ExecutorConfig {

    @Bean
    public ThreadPoolTaskExecutor seederExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(16);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Seeder-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }
}
