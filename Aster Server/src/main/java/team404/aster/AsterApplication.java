package team404.aster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "team404.aster")
public class AsterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsterApplication.class, args);
    }

}
