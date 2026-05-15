package com.buy.blobstorageimageapp.user.model.dto;

import lombok.Builder;

@Builder
public record UserLoginRequest(String email, String password) {
}
