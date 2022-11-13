package blog.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "Post")
public class Post {
    @Id
    private String id;

    private List<String> content;
    private String createdAt;

    public Post() {
        this.createdAt = LocalDateTime.now().toString();
    }
    public Post(List<String> content){
        this.content = content;
        this.createdAt = LocalDateTime.now().toString();
    }
    public Post(List<String> content, String createdAt){
        this.content = content;
        this.createdAt = createdAt;
    }
    public List<String> getContent() {
        return content;
    }
    public String getId(){
        return id;
    }
    public String getCreatedAt(){
        return createdAt;
    }
}