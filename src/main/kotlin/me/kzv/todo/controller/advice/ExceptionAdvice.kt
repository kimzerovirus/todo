package me.kzv.todo.controller.advice

import me.kzv.todo.exception.UnAuthorizationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(UnAuthorizationException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected fun unAuthorizationException(request: HttpServletRequest, e: UnAuthorizationException): ErrorResponse {
        return ErrorResponse(code = e.code, message = e.message)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun defaultException(request: HttpServletRequest, e: Exception): ErrorResponse {
        return ErrorResponse(code = 500, message = "서버 에러")
    }

}