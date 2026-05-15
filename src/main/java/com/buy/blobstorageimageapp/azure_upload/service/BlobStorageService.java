package com.buy.blobstorageimageapp.azure_upload.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.buy.blobstorageimageapp.azure_upload.dto.SasUrlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class BlobStorageService {

    private final BlobServiceClient blobServiceClient;
    private final String containerName;

    public BlobStorageService(
            BlobServiceClient blobServiceClient,
            @Value("${azure.storage.container-name}") String containerName ) {
        this.blobServiceClient = blobServiceClient;
        this.containerName = containerName;
    }

    public SasUrlResponse generateUploadSasUrl(String fileName, String contentType, Long userId) {


        String blobName = "users/" + userId + "/" + System.currentTimeMillis() + "-" + fileName;


        BlobContainerClient containerClient =
                blobServiceClient.getBlobContainerClient(containerName);

        BlobClient blobClient =
                containerClient.getBlobClient(blobName);

        BlobSasPermission permission = new BlobSasPermission()
                .setCreatePermission(true)
                .setWritePermission(true);

        OffsetDateTime expiryTime = OffsetDateTime.now().plusMinutes(10);

        BlobServiceSasSignatureValues values =
                new BlobServiceSasSignatureValues(expiryTime, permission)
                        .setContentType(contentType);

        String sasToken = blobClient.generateSas(values);

        String sasUrl = blobClient.getBlobUrl() + "?" + sasToken;

        return SasUrlResponse.builder()
                .sasUrl(sasUrl)
                .blobName(blobName)
                .build();
    }

    public String generateReadSasUrl(String blobName) {

        BlobContainerClient containerClient =
                blobServiceClient.getBlobContainerClient(containerName);

        BlobClient blobClient =
                containerClient.getBlobClient(blobName);

        BlobSasPermission permission =
                new BlobSasPermission()
                        .setReadPermission(true);

        OffsetDateTime expiryTime =
                OffsetDateTime.now().plusHours(1);

        BlobServiceSasSignatureValues values =
                new BlobServiceSasSignatureValues(
                        expiryTime,
                        permission
                );

        String sasToken =
                blobClient.generateSas(values);

        return blobClient.getBlobUrl() + "?" + sasToken;
    }
}
