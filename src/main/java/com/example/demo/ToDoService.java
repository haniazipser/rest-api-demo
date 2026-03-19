package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ToDoService {

    private final Map<Long, ToDoItem> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public Collection<ToDoItem> findAll() {
        return store.values();
    }

    public ToDoItem findById(Long id) {
        var item = store.get(id);
        if (item == null) {
            throw new ToDoItemNotFoundException(id);
        }
        return item;
    }

    public ToDoItem create(String description) {
        var item = new ToDoItem(idSequence.getAndIncrement(), description, false);
        store.put(item.id(), item);
        return item;
    }

    public ToDoItem update(Long id, String description, Boolean completed) {
        var item = store.get(id);
        if (item == null) throw new ToDoItemNotFoundException(id);

        var updated = new ToDoItem(
                item.id(),
                description != null ? description : item.description(),
                completed != null   ? completed   : item.completed()
        );

        store.put(id, updated);
        return updated;
    }

    public void delete(Long id) {
        if (store.remove(id) == null) {
            throw new ToDoItemNotFoundException(id);
        }
    }
}