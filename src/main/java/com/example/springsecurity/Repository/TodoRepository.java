package com.example.springsecurity.Repository;

import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository  extends JpaRepository<Todo,Integer> {
    List<Todo> findAllByUser(User user);
    Todo findTodoById(Integer todoId);
}
