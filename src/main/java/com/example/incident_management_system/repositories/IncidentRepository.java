package com.example.incident_management_system.repositories;

import com.example.incident_management_system.entities.IncidentEntity;
import com.example.incident_management_system.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<IncidentEntity, Long> {
    IncidentEntity findByIncidentId(Long incidentId);

    List<IncidentEntity> findByUser(UserEntity user);
}
