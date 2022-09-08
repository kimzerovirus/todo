package me.kzv.todo.service

import me.kzv.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService (
    private val todoRepository: TodoRepository,
){
    @Transactional
    fun create(){

    }
}