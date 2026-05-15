package com.buy.blobstorageimageapp.file.model;

import com.azure.core.http.ContentType;
import com.buy.blobstorageimageapp.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "photos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String url;

    private String originalFileName;

    private String contentType;

    private Long size;

    private LocalDate uploadedDate;
}
