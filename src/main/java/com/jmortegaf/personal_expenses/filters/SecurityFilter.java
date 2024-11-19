package com.jmortegaf.personal_expenses.filters;

import com.jmortegaf.personal_expenses.repositories.UserRepository;
import com.jmortegaf.personal_expenses.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

//    @Autowired
    private final TokenService tokenService;
//    @Autowired
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService,UserRepository userRepository){
        super();
        this.tokenService=tokenService;
        this.userRepository=userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token=request.getHeader("Authorization");
        if(token!=null) {
            token = token.replace("Bearer ", "");
            var tokenSubject = tokenService.getSubject(token);
            if (tokenSubject != null) {
                var user = userRepository.findByUserName(tokenSubject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
