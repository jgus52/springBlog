package blog;

import blog.auth.Admin;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static String JWT_SECRET;

    @Value("${JWT_SECRET}")
    public void setJWT_SECRET(String JWT_SECRET) {
        System.out.println(JWT_SECRET);
        this.JWT_SECRET = JWT_SECRET;
    }

    public static String generateToken(Admin admin) {
        Date now = new Date();
        Date expiryDate =new Date(now.getTime() + 3600000);

        System.out.println(JWT_SECRET);
        return Jwts.builder()
                .setSubject(admin.getId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static boolean validateToken(String token){
//        System.out.println(token);
        try{
//            System.out.println(Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject());
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

            return true;
        } catch(ExpiredJwtException e){ }
        catch(JwtException e){ }

        return false;
    }
}
