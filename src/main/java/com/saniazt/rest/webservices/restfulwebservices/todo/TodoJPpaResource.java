package com.saniazt.rest.webservices.restfulwebservices.todo;

import com.saniazt.rest.webservices.restfulwebservices.todo.repositories.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJPpaResource {

    private final TodoService todoService;
    private final TodoRepository todoRepository;

    public TodoJPpaResource(TodoService todoService, TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
      // return todoService.findByUsername(username);
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id){
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id){
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
                           @PathVariable int id, @RequestBody Todo todo) {
        //todoService.updateTodo(todo);
        todoRepository.save(todo);
        return todo;
    }
    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,
                           @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
        /*Todo createdTodo = todoService.addTodo(username, todo.getDescription(),
                todo.getTargetDate(),todo.isDone() );*/
    }

}
