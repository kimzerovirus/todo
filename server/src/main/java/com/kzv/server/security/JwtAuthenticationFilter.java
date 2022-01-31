package com.kzv.server.security;

import com.kzv.server.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    1. TokenProvider에서 토큰을 인증하고
    2. UsernamePasswordAuthenticationToken을 작성하여 이 오브젝트에 인증 정보를 저장한다.
    3. SecurityContext에 인증된 유저를 등록한다. -> 요청을 처리할 때 마다 유저의 인증 여부를 알 수 있게 해준다.
        기본적으로 이는 ThreadLocal에 저장되므로 쓰레드마다 하나의 컨텍스트를 관리할 수 있으며, 같은 쓰레드라면 어디에서든 접근 가능하다.
 */


@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    // 스프링 시큐리티 - 서블릿 필터 구현
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        try{
            //요청에서 토큰 가져오기
            String token = parseBearerToken(request);
            log.info("Filter is running...");
            //토큰 검사. JWT이므로 인가 서버에 요청없이 검증가능
            String userId = tokenProvider.validateAndGetUserId(token);
            if(token !=null && !token.equalsIgnoreCase("null")){
                log.info("Authenticated user ID: " + userId);
                //인증완료. SecurityContextHolder에 등록해야 인증된 사용자이다
                AbstractAuthenticationToken authenticationtoken = new UsernamePasswordAuthenticationToken(
                        userId, //인증된 사용자의 정보. 문자열이 아니어도 됨
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );
                authenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authenticationtoken);

                SecurityContextHolder.setContext(securityContext);
            }
        }catch(Exception e){
            logger.error("Could not set user authentication in security context", e);
        }

        filterChain.doFilter(request,response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        //Http 요청의 헤더를 파싱해 Bearer 토큰을 리턴한다.
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
