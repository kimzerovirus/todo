package me.kzv.todo.interceptor

import me.kzv.todo.exception.UnAuthorizationException
import me.kzv.todo.util.parseBearerToken
import me.kzv.todo.util.parseToken
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils.hasText
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = parseBearerToken(request) ?: throw UnAuthorizationException("토큰 인증 실패")
        parseToken(token)
        return true
    }

}