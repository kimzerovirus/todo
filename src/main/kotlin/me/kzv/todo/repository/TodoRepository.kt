package me.kzv.todo.repository

import me.kzv.todo.entity.Member
import me.kzv.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, String> {
    fun findByAuthor(author: Member): List<Todo>
}