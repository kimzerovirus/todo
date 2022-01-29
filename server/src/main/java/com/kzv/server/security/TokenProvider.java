package com.kzv.server.security;

import com.kzv.server.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "NMA8JPctFuna59f5";

    public String create(UserEntity userEntity){
        //기한 지금부터 1일
        Date expiryDate = Date.from(
                Instant.now().plus(1, ChronoUnit.DAYS)
        );

        /*
        *  {
        *       //header
        *       "alg":"HS512"
        *  },
        *
        * */

        //jwt token 생성
        return Jwts.builder()
                //header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                //payload에 들어갈 값
                .setSubject(userEntity.getId())
                .setIssuer("demo app") //iss
                .setIssuedAt(new Date()) //iat
                .setExpiration(expiryDate) //exp
                .compact();
    }

    public String validateAndGetUserId(String token){
        //parseClaimsJws 메서드가 base64로 디코딩 및 파싱을 함
        //헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후 token의 서명과 비교
        //위조되지 않았다면 페이로드(claims) 리턴, 위조라면 예외를 날린다.
        //userId를 getBody로 부른다.
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    //create() 토큰 생성 메서드
    //validateAndGetUserId()는 토크을 디코딩 및 파싱하고 토큰의 위조여부를 확인.
}
