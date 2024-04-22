package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import com.example.springsecurity.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    public List<Todo> getUserTodo(Integer userId){
        User user = authRepository.findUserById(userId);
        return todoRepository.findAllByUser(user);
    }

    public void addTodo(Integer userId, Todo todo){
        User user = authRepository.findUserById(userId);
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void updateTodo(Integer userId,Integer todoId, Todo todo){
//        User user = authRepository.findUserById(userId);
        Todo t = todoRepository.findTodoById(todoId);
        if(t == null){
            throw new ApiException("todo does not exists");
        }
        if(!t.getUser().getId().equals(userId)){
            throw new ApiException("can't update");
        }
        t.setTitle(todo.getTitle());
        t.setBody(todo.getBody());
        todoRepository.save(t);
    }

    public void deleteTodo(Integer userId, Integer todoId){
//        User user = authRepository.findUserById(userId);
        Todo todo = todoRepository.findTodoById(todoId);
        if(todo == null){
            throw new ApiException("todo does not exists");
        }
        if(!todo.getUser().getId().equals(userId)){
            throw new ApiException("can not delete");
        }
        todoRepository.deleteById(todoId);
    }

}