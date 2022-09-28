package me.kzv.todo.controller

import me.kzv.todo.controller.dto.TodoRequest
import me.kzv.todo.entity.Todo
import me.kzv.todo.service.TodoService
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/todo")
@RestController
class TodoController(
    private val todoService: TodoService
) {
    @PostMapping("/create")
    fun create(@RequestBody request: TodoRequest): List<Todo> = todoService.create(request)

    @PostMapping("/edit")
    fun edit(@RequestBody request: TodoRequest): List<Todo> = todoService.edit(request)

    @PostMapping("/delete")
    fun delete(@RequestBody request: TodoRequest): List<Todo> = todoService.delete(request)

    @PostMapping("/list")
    fun list(@RequestBody request: TodoRequest): List<Todo> = todoService.getList(request)

}