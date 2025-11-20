package com.technicalproject.Technical.Project.controller;

import com.technicalproject.Technical.Project.Request.SignUpRequest;
import com.technicalproject.Technical.Project.Request.UpdateUserRequest;
import com.technicalproject.Technical.Project.Response.ApiResponse;
import com.technicalproject.Technical.Project.Response.SignUpResponse;
import com.technicalproject.Technical.Project.model.User;
import com.technicalproject.Technical.Project.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> fetchUser(@PathVariable Long userId){
        try{
            User user = userService.getUser(userId);
            return ResponseEntity.ok(new ApiResponse(null,user));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId,@RequestBody UpdateUserRequest request){
        try {
            userService.updateUser(userId, request);
            return ResponseEntity.ok(new ApiResponse("User updated successfully. Hit view api to see changes",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("please dont leave email or password empty",e));
        }
    }

    @DeleteMapping("user/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("User deleted successfully.",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
