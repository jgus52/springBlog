package blog.post;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Post {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String content[];
    @JsonProperty
    private Timestamp createdAt;

    public Integer getId(){
        return id;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public String[] getContent(){
        return this.content;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCreatedAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }

    public void setContent(String[] content){
        this.content = content;
    }

    Post(){}

    Post(Integer id, String content[], java.sql.Timestamp createdAt){
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }
}