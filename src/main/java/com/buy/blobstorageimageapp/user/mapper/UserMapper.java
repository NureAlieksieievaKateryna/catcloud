package com.buy.blobstorageimageapp.user.mapper;

import com.buy.blobstorageimageapp.user.model.UserEntity;
import com.buy.blobstorageimageapp.user.model.dto.UserRegisterRequest;
import com.buy.blobstorageimageapp.user.model.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserMapper {

    public UserEntity toEntity(UserRegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setLogin(request.login());
        user.setEmail(request.email());
        user.setHashPass(request.password());
        return user;
    }

    public UserResponse toResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }
}
