package com.sideprojects.todo.service;

import com.sideprojects.todo.entity.Todo;
import com.sideprojects.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> findAll(String title){
        if (title != null){
            return todoRepository.findByTitleContaining(title);
        }
        return todoRepository.findAll();
    }

    public Todo findById(long id){
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.isPresent() ? todo.get() : null;
    }

    public List<Todo> findByCompleted(Boolean completed){
        return todoRepository.findByCompleted(completed);
    }

    public Todo save(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteById(long id){
        todoRepository.deleteById(id);
    }

    public void deleteAll(){
        todoRepository.deleteAll();
    }
}
