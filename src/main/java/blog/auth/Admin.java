package blog.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Admin {
    private final String id;
    private final String pwd;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public Admin(@Value("${id}") String id, @Value("${password}") String pwd){
        this.id = id;
        this.pwd = encoder.encode(pwd);
    }
    public boolean match(String id, String pwd){
        if(!id.equals(this.id)) return false;
        if(!encoder.matches(pwd, this.pwd)) return false;

        return true;
    }

    public String getId(){ return this.id; }
}