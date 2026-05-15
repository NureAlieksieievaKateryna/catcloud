package com.buy.blobstorageimageapp.controller;

import com.buy.blobstorageimageapp.azure_upload.dto.SasUrlResponse;
import com.buy.blobstorageimageapp.azure_upload.service.BlobStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("files")
@RestController()
@RequiredArgsConstructor
public class FileUploader {
    private final BlobStorageService blobStorageService;

    @GetMapping("/sas-url")
    public SasUrlResponse generateSasUrl(
            @RequestParam String fileName,
            @RequestParam String contentType,
            @RequestParam Long userId) {

        return blobStorageService.generateUploadSasUrl(
                fileName,
                contentType,
                userId
        );
    }

    @GetMapping("/read-url")
    public String generateReadUrl(
            @RequestParam String blobName
    ) {
        return blobStorageService.generateReadSasUrl(blobName);
    }
}
