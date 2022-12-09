package hu.elte.feladatnyilvantarto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FeladatnyilvantartoApplication {

	@Autowired
    private AddUserTest addUserTest;
	
	 @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
       return args -> addUserTest.AddUsers();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(FeladatnyilvantartoApplication.class, args);
	}

}
