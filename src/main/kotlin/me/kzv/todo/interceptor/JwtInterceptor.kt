package me.kzv.todo.interceptor

import me.kzv.todo.advice.exception.UnAuthorizationException
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils.hasText
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        parseBearerToken(request) ?: throw UnAuthorizationException("토큰 인증 실패")
        return true
    }


    private fun parseBearerToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            bearerToken.substring(7)
        } else null
    }

}