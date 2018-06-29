package com.sivalabs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Siva
 *
 */

@SpringBootApplication
//@MapperScan("com.sivalabs.demo.mappers")
@Import({ DefaultDatabaseConfig.class })
public class SpringbootMyBatisDemoApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(SpringbootMyBatisDemoApplication.class, args);
	}

}
