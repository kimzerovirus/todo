package me.kzv.todo.controller

import me.kzv.todo.dto.TodoRequest
import me.kzv.todo.entity.Todo
import me.kzv.todo.service.TodoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController (
    private val todoService: TodoService
){
    @PostMapping("/create")
    fun create(@RequestBody request: TodoRequest): List<Todo> = todoService.create(request)

    @PostMapping("/edit")
    fun edit(@RequestBody request: TodoRequest): List<Todo> = todoService.edit(request)

    @PostMapping("/delete")
    fun delete(@RequestBody request: TodoRequest) {
        todoService.delete(request)
    }
}