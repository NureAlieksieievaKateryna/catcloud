package com.buy.blobstorageimageapp.user.model.dto;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String login, String email) {
}
