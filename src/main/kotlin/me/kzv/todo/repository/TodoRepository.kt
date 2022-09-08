package me.kzv.todo.repository

import me.kzv.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, String> {
}