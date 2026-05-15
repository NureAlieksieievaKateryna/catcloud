package com.buy.blobstorageimageapp.azure_upload.dto;

import lombok.Builder;

@Builder
public record SasUrlResponse(
        String sasUrl,
        String blobName
) {
}
