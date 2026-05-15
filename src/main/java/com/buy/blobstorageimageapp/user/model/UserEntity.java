package com.buy.blobstorageimageapp.user.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String login;

    private String hashPass;
}