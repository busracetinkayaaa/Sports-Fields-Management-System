package com.app.SportsFieldManagement.mapper;

import com.app.SportsFieldManagement.dto.request.RegisterRequest;
import com.app.SportsFieldManagement.dto.response.UserResponse;
import com.app.SportsFieldManagement.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(),user.getUsername(),user.getRole());
    }
    public User toEntity(RegisterRequest request,String encodedPassword){
        return User.builder().username(request.username())
                .password(encodedPassword)
                .role("USER").build();
    }

}
