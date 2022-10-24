package blog;

import blog.auth.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends GenericFilterBean {
    Admin admin;

    @Autowired
    JwtAuthenticationFilter(Admin admin){
        this.admin = admin;
    }

    private String resolveToken(HttpServletRequest request){
        if(request.getHeader("Authorization") == null) return null;
        return request.getHeader("Authorization").substring("Bearer ".length());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) request);
        if(token != null && JwtTokenProvider.validateToken(token)){
//            System.out.println(token);
            //토큰이 유효하다면
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(admin.getId(), "", roles);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        }
        filterChain.doFilter(request, response);
    }
}
