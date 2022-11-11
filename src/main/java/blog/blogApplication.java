package blog;

import blog.post.PostRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "blog.post")
public class blogApplication {
    public static void main(String[] args) {
        SpringApplication.run(blogApplication.class, args);
    }
}