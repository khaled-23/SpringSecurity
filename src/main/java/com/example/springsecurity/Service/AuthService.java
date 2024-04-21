package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.DTO.LoginDTO;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final MyUserDetailsService myUserDetailsService;


    public void register(User user){
        user.setRole("ADMIN");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

    public List<User> getAllUsers(){
        return authRepository.findAll();
    }
//    public User getUserByUsername(String username){
//        return (User) myUserDetailsService.loadUserByUsername(username);
//    }

    public void deleteUser(String username){
        User user = authRepository.findUsersByUsername(username);
        if(user == null){
            throw new ApiException("user does not exists");
        }
        authRepository.delete(user);
    }

    public void updateUser(String username, User user){
        User u = authRepository.findUsersByUsername(username);
        if(user == null){
            throw new ApiException("user does not exists");
        }
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        u.setUsername(user.getUsername());
        u.setPassword(hashPassword);
        authRepository.save(u);
    }

    public void login(LoginDTO loginDTO){
//        User user = (User) myUserDetailsService.loadUserByUsername(loginDTO.getUsername());
//        if(!new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.getPassword())){
//            throw new ApiException("username or password is invalid");
//        }
    }
    public void logout(){

    }
}
