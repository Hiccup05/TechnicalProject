package com.technicalproject.Technical.Project.service.user;

import com.technicalproject.Technical.Project.Request.SignUpRequest;
import com.technicalproject.Technical.Project.Request.UpdateUserRequest;
import com.technicalproject.Technical.Project.model.User;

import java.util.List;

public interface IUserService {

    User getUser(Long id);

    List<User> getUsers();

    void updateUser(Long id, UpdateUserRequest request);
    void deleteUser(Long id);
}
