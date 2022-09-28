package me.kzv.todo.controller.dto

data class TodoRequest (
    val id: String? = null,
    val todo: String,
    val isFinished: Boolean,
    val authorId: String,
)

data class TodoListRequest (
    val authorId: String,
)

data class TodoDeleteRequest(
    val id: String,
    val authorId: String,
)