package com.example.springsecurity.Controller;


import com.example.springsecurity.Api.ApiResponse;
import com.example.springsecurity.DTO.LoginDTO;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("registered"));
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PutMapping("/user/update/{username}")
    public ResponseEntity updateUser(@PathVariable String username,@RequestBody @Valid User user){
        authService.updateUser(username,user);
        return ResponseEntity.ok(new ApiResponse("user updated"));
    }

    //login logout crud

    @DeleteMapping("/user/delete/{username}")
    public ResponseEntity deleteUser(@PathVariable String username){
        authService.deleteUser(username);
        return ResponseEntity.ok(new ApiResponse("user deleted"));
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        authService.login(loginDTO);
        return ResponseEntity.ok(new ApiResponse("welcome"));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        authService.logout();
        return ResponseEntity.ok(new ApiResponse("logged out successfully"));
    }

//    @GetMapping("/user/{username}")
//    public ResponseEntity login(@PathVariable String username){
//        return ResponseEntity.ok(authService.getUserByUsername(username));
//    }


}
