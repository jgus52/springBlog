package blog.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin {
    @JsonProperty
    private String id;
    @JsonProperty
    private String pwd;

    public String getPwd(){
        return this.pwd;
    }
    public String getId(){
        return this.id;
    }

    public void setPwd(String hashed){
        this.pwd = hashed;
    }

    public void setId(String id){
        this.id = id;
    }
}
