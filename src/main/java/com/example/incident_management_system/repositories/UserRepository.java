package com.example.incident_management_system.repositories;

import com.example.incident_management_system.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Long aLong);

    boolean existsByEmailId(String emailId);
}
