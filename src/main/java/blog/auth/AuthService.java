package blog.auth;

import blog.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    PasswordEncoder passwordEncoder;
    Admin admin;

    @Autowired
    AuthService(Admin admin){
        this.admin = admin;
    }

    public String authenticate(String id, String pwd){
        if(admin.match(id, pwd)){
            return JwtTokenProvider.generateToken(admin);
        }
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong Id or Pwd");
    }
}
