package com.example.springsecurity.Repository;

import com.example.springsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {

    User findUsersByUsername(String username);

    User findUserById(Integer userId);

}
