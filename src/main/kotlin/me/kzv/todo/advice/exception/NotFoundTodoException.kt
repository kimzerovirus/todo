package me.kzv.todo.advice.exception

import java.lang.RuntimeException

data class NotFoundTodoException (
    override val message: String,
    val code: Int,
) : RuntimeException(message)