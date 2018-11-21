package com.social;

import com.social.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;

/**
 * 
 * @author kamal berriga
 *
 */
@SpringBootApplication
public class SpringBootSocialAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSocialAuthApplication.class, args);
	}



}
