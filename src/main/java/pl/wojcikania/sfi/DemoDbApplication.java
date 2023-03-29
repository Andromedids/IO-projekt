package pl.wojcikania.sfi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDbApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.default", "local");
		SpringApplication.run(DemoDbApplication.class, args);
	}
}
