package com.technicalproject.Technical.Project.service.user;


import com.technicalproject.Technical.Project.Repository.UserRepository;
import com.technicalproject.Technical.Project.Request.SignUpRequest;
import com.technicalproject.Technical.Project.Request.UpdateUserRequest;
import com.technicalproject.Technical.Project.exception.ResourceAlreadyFound;
import com.technicalproject.Technical.Project.exception.ResourceNotFoundException;
import com.technicalproject.Technical.Project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User nor found"));
    }

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long id, UpdateUserRequest request) {
       userRepository.findById(id).ifPresentOrElse
                (user1 -> {
                    user1.setUserName(request.getUserName());
                    user1.setPassword(request.getPassword());
                    user1.setFirstName(request.getFirstName());
                    user1.setLastName(request.getLastName());
                    userRepository.save(user1);
                    },()->{throw new ResourceNotFoundException("User not found");}
                );
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(
                        userRepository::delete,
                        ()-> {throw new ResourceNotFoundException("User may have been deleted already or never existed");}
                );
    }
}
