package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private final ToDoService todoService;

    public ToDoController(ToDoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public Collection<ToDoItem> getAll() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public ToDoItem getById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoItem create(@RequestBody CreateRequest req) {
        return todoService.create(req.description());
    }

    @PatchMapping("/{id}")
    public ToDoItem update(@PathVariable Long id,
                           @RequestBody UpdateRequest req) {
        return todoService.update(id, req.description(), req.completed());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }

}