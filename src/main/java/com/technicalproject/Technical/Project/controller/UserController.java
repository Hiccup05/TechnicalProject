package com.technicalproject.Technical.Project.controller;

import com.technicalproject.Technical.Project.Request.UpdateUserRequest;
import com.technicalproject.Technical.Project.Response.ApiResponse;
import com.technicalproject.Technical.Project.model.CustomUser;
import com.technicalproject.Technical.Project.model.User;
import com.technicalproject.Technical.Project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> fetchUser(@AuthenticationPrincipal CustomUser authenticatedUser){
        try{
            User user = userService.getUser(authenticatedUser.getId());
            return ResponseEntity.ok(new ApiResponse(null,user));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity<ApiResponse> updateUser(@AuthenticationPrincipal CustomUser authenticatedUser,@RequestBody UpdateUserRequest request){
        try {
            userService.updateUser(authenticatedUser.getId(), request);
            return ResponseEntity.ok(new ApiResponse("User updated successfully. Hit view api to see changes",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("please dont leave email or password empty",e));
        }
    }

    @DeleteMapping("user/delete")
    public ResponseEntity<ApiResponse> deleteUser(@AuthenticationPrincipal CustomUser authenticatedUser){
        try {
            userService.deleteUser(authenticatedUser.getId());
            return ResponseEntity.ok(new ApiResponse("User deleted successfully.",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
