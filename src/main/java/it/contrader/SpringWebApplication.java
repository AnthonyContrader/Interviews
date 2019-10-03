package it.contrader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.SpringApplication;

import it.contrader.controller.InterceptorController;
import it.contrader.services.UserService;

@SpringBootApplication
public class SpringWebApplication implements WebMvcConfigurer{

	static UserService userService;

	@Autowired
	public SpringWebApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new InterceptorController());
	}
}
