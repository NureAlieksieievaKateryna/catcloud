package com.buy.blobstorageimageapp.user.repository;

import com.buy.blobstorageimageapp.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    Optional<UserEntity> findByEmail(String email);
}
