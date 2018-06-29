package com.sivalabs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Siva
 *
 */

@SpringBootApplication
@Import({ DefaultDatabaseConfig.class })
public class SpringbootJdbcDemoApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(SpringbootJdbcDemoApplication.class, args);
	}

}
