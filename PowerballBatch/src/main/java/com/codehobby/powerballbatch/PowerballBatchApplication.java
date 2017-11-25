package com.codehobby.powerballbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PowerballBatchApplication
{

	public static void main(String[] args)
	{
		//SpringApplication.run(PowerballBatchApplication.class, args);
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(PowerballBatchApplication.class)
				//.properties("spring.config.name:application,config")
				.build().run(args);
	}
}
