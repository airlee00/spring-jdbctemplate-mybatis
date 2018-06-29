package com.sivalabs.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sivalabs.demo.domain.User;
import com.sivalabs.demo.mappers.UserMapper;

/**
 * @author Siva
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootMyBatisDemoApplication.class })
public class SpringbootMyBatisDemoApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(SpringbootMyBatisDemoApplicationTests.class);
	
	@Autowired
	private UserMapper userMapper;
	// @Autowired private UserAnnotationMapper userMapper;

	private int LOOP = 1000;
	private int SLEEP = 120000;
	
	@Test
	public void findAllUsers() throws InterruptedException {
		logger.info("setup===============================");
		Thread.sleep(10000);
		logger.info("start===============================");
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			List<User> users = userMapper.findAllUsers();
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
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			User user = userMapper.findUserById(i);
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
		for (int i = 0; i < LOOP; i++) {
			User user = new User(i , "Siva", "siva@gmail.com");
			userMapper.insertUser(user);
		}
		long time2 = System.currentTimeMillis();
		long timeSpent = time2 - time1;
		logger.info("took " + timeSpent + " ns");
		Thread.sleep(SLEEP);

	}
}
