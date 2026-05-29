package com.buy.blobstorageimageapp.user;

import com.buy.blobstorageimageapp.azure_upload.dto.SasUrlResponse;
import com.buy.blobstorageimageapp.azure_upload.service.BlobStorageService;
import com.buy.blobstorageimageapp.user.model.UserEntity;
import com.buy.blobstorageimageapp.user.model.dto.UserLoginRequest;
import com.buy.blobstorageimageapp.user.model.dto.UserRegisterRequest;
import com.buy.blobstorageimageapp.user.model.dto.UserResponse;
import com.buy.blobstorageimageapp.user.repository.UserRepository;
import com.buy.blobstorageimageapp.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BlobStorageService blobStorageService;

    @Test
    void shouldRegisterUser() {
        String unique = String.valueOf(System.currentTimeMillis());

        String email = "test" + unique + "@gmail.com";
        String login = "test" + unique;
        String password = "123456";

        UserRegisterRequest request =
                new UserRegisterRequest(
                        email,
                        login,
                        password
                );

        UserResponse user =
                userService.registerUser(request);

        assertNotNull(user);
        assertNotNull(user.id());
    }

    @Test
    void shouldLoginUser() {
        String unique = String.valueOf(System.currentTimeMillis());

        String email = "test" + unique + "@gmail.com";
        String login = "test" + unique;
        String password = "123456";

        userService.registerUser(
                new UserRegisterRequest(
                        email,
                        login,
                        password
                )
        );

        UserLoginRequest request =
                new UserLoginRequest(
                        email,
                        password
                );

        UserResponse dto =
                userService.loginUser(request);

        assertNotNull(dto);
        assertNotNull(dto.id());
    }

    @Test
    void shouldGenerateSasUrl() {
        SasUrlResponse sasUrl =
                blobStorageService.generateUploadSasUrl(
                        "cat.jpg",
                        "image/jpeg",
                        1L
                );

        assertNotNull(sasUrl);
    }
}