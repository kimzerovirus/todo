package me.kzv.todo.controller.dto

data class TodoRequest (
    val id: String,
    val todo: String,
    val isFinished: Boolean,
    val authorId: String,
)