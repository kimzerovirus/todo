package me.kzv.todo.dto

import me.kzv.todo.entity.Member

data class MemberRequest(
    val username: String,
    val userId: String,
    val password: String,
)

data class MemberResponse(
    val username: String,
    val userId: String,
    val token: String,
)

fun Member.toResponse(token: String) = MemberResponse(
    userId = userId,
    username = username,
    token = token, // TODO JWT TOKEN
)