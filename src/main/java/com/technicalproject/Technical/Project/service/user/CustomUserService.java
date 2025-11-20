package com.technicalproject.Technical.Project.service.user;

import com.technicalproject.Technical.Project.Repository.UserRepository;
import com.technicalproject.Technical.Project.exception.ResourceNotFoundException;
import com.technicalproject.Technical.Project.model.CustomUser;
import com.technicalproject.Technical.Project.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username).orElseThrow(()->new ResourceNotFoundException("User didn't found"));
        return modelMapper.map(user,CustomUser.class);
    }
}
