package blog.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin {
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String pwd;

    public Admin(String id,String pwd){
        this.id = id;
        this.pwd = pwd;
    }
    public String getPwd(){
        return this.pwd;
    }
    public String getId(){
        return this.id;
    }
}