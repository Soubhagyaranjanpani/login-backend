package com.example.backendp;

import com.example.backendp.model.Role;
import com.example.backendp.model.User;
import com.example.backendp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendpApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(BackendpApplication.class, args);
	}

	public void run(String... args){
    User adminAccount=userRepo.findByRole(Role.ADMIN);
	if (null==adminAccount){
		User user=new User();

			user.setEmail("admin@gmail.com");
			user.setName("admin");
			user.setPhoneNumber("9876543210");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setGender("male");
			user.setRole(Role.ADMIN);
			userRepo.save(user);


		}
	}
}
