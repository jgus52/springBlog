package blog.post;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    public List<Post> findAllByOrderByCreatedAtDesc();
}
