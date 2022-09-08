package me.kzv.todo.advice.exception

import java.lang.RuntimeException

data class UnAuthorizationException (
    override val message: String,
    val code: Int = 401,
    ) : RuntimeException(message)