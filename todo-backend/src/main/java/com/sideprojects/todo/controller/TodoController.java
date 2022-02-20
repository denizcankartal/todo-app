package com.sideprojects.todo.controller;

import com.sideprojects.todo.entity.Todo;
import com.sideprojects.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(@RequestBody(required = false) String title){
        List<Todo> todos = todoService.findAll(title);
        if (todos.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") long id){
        Todo todo = todoService.findById(id);
        if (todo == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping("/todos/not-completed")
    public ResponseEntity<List<Todo>> getNotCompletedTodos(){
        List<Todo> notCompletedTodos = todoService.findByCompleted(false);
        if (notCompletedTodos.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(notCompletedTodos, HttpStatus.OK);
    }

    @GetMapping("/todos/completed")
    public ResponseEntity<List<Todo>> getCompletedTodos(){
        List<Todo> completedTodos = todoService.findByCompleted(true);
        if (completedTodos.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(completedTodos, HttpStatus.OK);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        try{
            Todo savedTodo = todoService.save(new Todo(todo.getTitle(), todo.getDescription(), false));
            return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") long id, @RequestBody Todo todo){
        Todo targetTodo = todoService.findById(id);
        if (targetTodo != null){
            targetTodo.setTitle(todo.getTitle());
            targetTodo.setDescription(todo.getDescription());
            targetTodo.setCompleted(todo.isCompleted());
            return new ResponseEntity<>(todoService.save(targetTodo), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") long id){
        todoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/todos")
    public ResponseEntity<HttpStatus> deleteAllTodos(){
        todoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
