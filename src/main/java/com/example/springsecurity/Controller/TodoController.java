package com.example.springsecurity.Controller;

import com.example.springsecurity.Api.ApiResponse;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal User user, @RequestBody @Valid Todo todo){
        todoService.addTodo(user.getId(), todo);
        return ResponseEntity.ok(new ApiResponse("todo added"));
    }

    @GetMapping("/todos")
    public ResponseEntity getAllTodos(){
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/user-todos")
    public ResponseEntity getUserTodos(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(todoService.getUserTodo(user.getId()));
    }

    @PutMapping("/update/{todo_id}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal User user, @PathVariable Integer todo_id,
                                     @RequestBody @Valid Todo todo){
        todoService.updateTodo(user.getId(), todo_id,todo);
        return ResponseEntity.ok(new ApiResponse("todo updated"));
    }

    @DeleteMapping("/delete/{todo_id}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal User user, @PathVariable Integer todo_id){
        todoService.deleteTodo(user.getId(), todo_id);
        return ResponseEntity.ok(new ApiResponse("todo deleted"));
    }
}
