package me.kzv.todo.service

import me.kzv.todo.controller.dto.TodoRequest
import me.kzv.todo.entity.Member
import me.kzv.todo.entity.Todo
import me.kzv.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService (
    private val todoRepository: TodoRepository,
){
    @Transactional
    fun create(request: TodoRequest) : List<Todo> {
        val author = Member(request.authorId)
        val todo = Todo(
            todo = request.todo,
            author = author
        )
        todoRepository.save(todo)

        return todoRepository.findByAuthor(author)
    }

    @Transactional
    fun edit(request: TodoRequest): List<Todo> {
        val author = Member(request.authorId)
        todoRepository.findByIdOrNull(request.id)?.run {
            todo = request.todo
            todoRepository.save(this)
        }

        return todoRepository.findByAuthor(author)
    }

    @Transactional
    fun delete(request: TodoRequest) {
        todoRepository.deleteById(request.id)
    }
}