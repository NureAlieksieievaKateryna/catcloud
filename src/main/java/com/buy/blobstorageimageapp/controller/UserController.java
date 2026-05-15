package com.buy.blobstorageimageapp.controller;

import com.buy.blobstorageimageapp.user.model.dto.UserLoginRequest;
import com.buy.blobstorageimageapp.user.model.dto.UserRegisterRequest;
import com.buy.blobstorageimageapp.user.model.dto.UserResponse;
import com.buy.blobstorageimageapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController()
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLoginRequest request) {
        return userService.loginUser(request);
    }
}
