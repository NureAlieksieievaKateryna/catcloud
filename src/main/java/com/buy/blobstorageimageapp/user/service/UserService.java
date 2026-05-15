package com.buy.blobstorageimageapp.user.service;

import com.buy.blobstorageimageapp.user.mapper.UserMapper;
import com.buy.blobstorageimageapp.user.model.UserEntity;
import com.buy.blobstorageimageapp.user.model.dto.UserLoginRequest;
import com.buy.blobstorageimageapp.user.model.dto.UserRegisterRequest;
import com.buy.blobstorageimageapp.user.model.dto.UserResponse;
import com.buy.blobstorageimageapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse registerUser(UserRegisterRequest registerRequest) {
        this.checkUserUniqueness(registerRequest);

        UserEntity user = userMapper.toEntity(registerRequest);

        UserEntity savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    private void checkUserUniqueness(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByLogin(request.login())) {
            throw new RuntimeException("Login already exists");
        }
    }

    public UserResponse loginUser(UserLoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getHashPass().equals(request.password())) {
            throw new RuntimeException("Invalid password");
        }

        return userMapper.toResponse(user);
    }
}
