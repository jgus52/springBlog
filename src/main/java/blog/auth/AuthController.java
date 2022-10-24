package blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {
    AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/auth")
    public String login(@RequestBody Admin body) {
        try{
            return authService.authenticate(body.getId(), body.getPwd());
        }catch(ResponseStatusException e){
            throw e;
        }
    }
}
