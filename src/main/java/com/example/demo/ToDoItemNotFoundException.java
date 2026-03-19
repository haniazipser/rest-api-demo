package com.example.demo;

public class ToDoItemNotFoundException extends RuntimeException {
    public ToDoItemNotFoundException(Long id) {
        super("Todo not item found: " + id);
    }
}