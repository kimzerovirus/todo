package me.kzv.todo.exception

import java.lang.RuntimeException

data class NotFoundTodoException (
    override val message: String,
    val code: Int,
) : RuntimeException(message)