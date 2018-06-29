package com.sivalabs.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author Siva
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootJdbcDemoApplication.class })
public class SpringbootJdbcDemoApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(SpringbootJdbcDemoApplicationTests.class);
			
	@Autowired
	private UserRepository userRepository;

	private int LOOP = 1000;
	private int SLEEP = 120000;
	
	@Test
	public void findAllUsers() throws InterruptedException {
		logger.info("setup===============================");
		Thread.sleep(10000);
		logger.info("start===============================");
		long time1 = System.currentTimeMillis();// .nanoTime();
		for (int i = 0; i < LOOP; i++) {
			List<User> users = userRepository.findAll();
			//System.out.println(users);
		}
		long time2 = System.currentTimeMillis();
		long timeSpent = time2 - time1;
		logger.info("took " + timeSpent + " ns");
		Thread.sleep(SLEEP);
	}

	@Test
	public void findUserById() throws InterruptedException {
		logger.info("setup===============================");
		Thread.sleep(10000);
		logger.info("start===============================");
		long time1 = System.currentTimeMillis();// .nanoTime();
		for (int i = 0; i < LOOP; i++) {
			User user = userRepository.findUserById(i);
		}
		long time2 = System.currentTimeMillis();
		long timeSpent = time2 - time1;
		logger.info("took " + timeSpent + " ns");
		Thread.sleep(SLEEP);
	}

	@Test
	public void createUser() throws InterruptedException {
		logger.info("setup===============================");
		Thread.sleep(10000);
		logger.info("start===============================");
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			User user = new User(i , "Siva2", "siva@gmail.com");
			User savedUser = userRepository.create(user);
		}
		long time2 = System.currentTimeMillis();
		long timeSpent = time2 - time1;
		logger.info("took " + timeSpent + " ns");
		Thread.sleep(SLEEP);

	}
}
