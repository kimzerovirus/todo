package me.kzv.todo.dto

data class TodoRequest (
    val id: String,
    val todo: String,
    val isFinished: Boolean,
)