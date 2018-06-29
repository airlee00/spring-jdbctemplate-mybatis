package com.sivalabs.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sivalabs.demo.domain.User;
import com.sivalabs.demo.mappers.UserMapper;

@Controller
public class TestController {
	  
	private final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private UserMapper userMapper;
	
	private int LOOP = 1000;
	
	@GetMapping("/all/{loop}")
	  public @ResponseBody String findAllUsers(@PathVariable Integer loop ) {
		long time1 = System.currentTimeMillis();// .nanoTime();
		for (int i = 0; i < loop ; i++) {
			List<User> users = userMapper.findAllUsers();
		}
		long time2 = System.currentTimeMillis();
		long timeSpent = time2 - time1;
		logger.info("took " + timeSpent + " ns");
	    return "loop:" + loop + ", took " + timeSpent + " ns";
	  }
	
	@GetMapping("/byid/{loop}")
	  public @ResponseBody String findUserById(@PathVariable Integer loop ) {
		long time1 = System.currentTimeMillis();// .nanoTime();
		for (int i = 0; i < loop ; i++) {
			User user = userMapper.findUserById(i);
		}
		long time2 = System.currentTimeMillis();
		long timeSpent = time2 - time1;
		logger.info("took " + timeSpent + " ns");
	    return "loop:" + loop + ", took " + timeSpent + " ns";
	  }
	
	@GetMapping("/create/{loop}")
	  public @ResponseBody String create(@PathVariable Integer loop ) {
		long time1 = System.currentTimeMillis();// .nanoTime();
		for (int i = 0; i < loop ; i++) {
			User user = new User(i , "Siva", "siva@gmail.com");
			userMapper.insertUser(user);
		}
		long time2 = System.currentTimeMillis();
		long timeSpent = time2 - time1;
		logger.info("took " + timeSpent + " ns");
	    return "loop:" + loop + ", took " + timeSpent + " ns";
	  }
}
