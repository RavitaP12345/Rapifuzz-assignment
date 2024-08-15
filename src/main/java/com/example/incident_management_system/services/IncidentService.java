package com.example.incident_management_system.services;

import com.example.incident_management_system.entities.IncidentEntity;
import com.example.incident_management_system.entities.UserEntity;
import com.example.incident_management_system.models.IncidentModel;
import com.example.incident_management_system.models.UserModel;
import com.example.incident_management_system.repositories.IncidentRepository;
import com.example.incident_management_system.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    @Transactional
    public ResponseEntity<?> saveIncidentDetails(IncidentModel incidentModel) {
        UserEntity userEntity = userRepository.findByUserId(Long.valueOf(incidentModel.getUserId()));
        if(userEntity==null){
            return new ResponseEntity<>("User id is invalid !", HttpStatus.FORBIDDEN);
        }
        IncidentEntity incidentEntity = new IncidentEntity();
        incidentEntity.setReporterName(userEntity.getName());
        incidentEntity.setIncidentDetails(incidentModel.getIncidentDetails());
        incidentEntity.setPriority(incidentModel.getPriority());
        incidentEntity.setStatus(incidentModel.getStatus());
        incidentEntity.setEnterpriseOrGovernment(incidentModel.getEnterpriseOrGovernment());
        incidentEntity.setReportedDateTime(new Date());
        UserEntity user = userRepository.findByUserId(Long.valueOf(incidentModel.getUserId()));
        incidentEntity.setUser(user);
        IncidentEntity incident = incidentRepository.save(incidentEntity);
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        String customId =  "RMG"+incident.getIncidentId()+String.valueOf(year);
        incident.setCustomUniqueIncidentId(customId);
        incidentRepository.save(incident);
        return new ResponseEntity<>("Saved", HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<?> editIncidentDetails(IncidentModel incidentModel) {
        if(incidentModel.getIncidentId() == null){
            return new ResponseEntity<>("Incident id does not exist.", HttpStatus.FORBIDDEN);
        }
        IncidentEntity incidentEntity = incidentRepository.findByIncidentId(incidentModel.getIncidentId());
        if(incidentEntity.getStatus().equalsIgnoreCase("Close")){
            return new ResponseEntity<>("Already closed you can not update this incident.", HttpStatus.FORBIDDEN);
        }else {
            incidentEntity.setIncidentDetails(incidentModel.getIncidentDetails());
            incidentEntity.setReportedDateTime(new Date());
            incidentEntity.setEnterpriseOrGovernment(incidentModel.getEnterpriseOrGovernment());
            incidentEntity.setPriority(incidentModel.getPriority());
            incidentEntity.setStatus(incidentModel.getStatus());
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }

    }

    public IncidentModel getIncidentByIncidentId(String incidentId) {
        IncidentEntity incidentEntity = incidentRepository.findByIncidentId(Long.valueOf(incidentId));
        IncidentModel incidentModel = new IncidentModel();
        incidentModel.setIncidentId(incidentEntity.getIncidentId());
        incidentModel.setCustomUniqueIncidentId(incidentEntity.getCustomUniqueIncidentId());
        incidentModel.setIncidentDetails(incidentEntity.getIncidentDetails());
        incidentModel.setPriority(incidentEntity.getPriority());
        incidentModel.setUserId(String.valueOf(incidentEntity.getUser().getUserId()));
        incidentModel.setStatus(incidentEntity.getStatus());
        incidentModel.setEnterpriseOrGovernment(incidentEntity.getEnterpriseOrGovernment());
        incidentModel.setReportedDateTime(incidentEntity.getCustomUniqueIncidentId());
        UserEntity user = userRepository.findByUserId(incidentEntity.getUser().getUserId());
        incidentModel.setReporterName(user.getName());
        UserModel userModel = new UserModel();
        userModel.setUserId(user.getUserId());
        userModel.setName(user.getName());
        userModel.setAddress(user.getAddress());
        userModel.setCity(user.getCity());
        userModel.setEmailId(user.getEmailId());
        userModel.setCountry(user.getCountry());
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setPinCode(user.getPinCode());
        incidentModel.setUser(userModel);
        return incidentModel;
    }

    public List<IncidentModel> getIncidentsByUserId(String userId) {
        UserEntity user = userRepository.findByUserId(Long.valueOf(userId));
        List<IncidentEntity> incidentEntities = incidentRepository.findByUser(user);
        List<IncidentModel> incidentModels = new ArrayList<>();
        incidentEntities.forEach(data->{
            IncidentModel incidentModel = new IncidentModel();
            incidentModel.setIncidentId(data.getIncidentId());
            incidentModel.setCustomUniqueIncidentId(data.getCustomUniqueIncidentId());
            incidentModel.setIncidentDetails(data.getIncidentDetails());
            incidentModel.setPriority(data.getPriority());
            incidentModel.setUserId(String.valueOf(data.getUser().getUserId()));
            incidentModel.setStatus(data.getStatus());
            incidentModel.setEnterpriseOrGovernment(data.getEnterpriseOrGovernment());
            incidentModel.setReportedDateTime(data.getCustomUniqueIncidentId());
            incidentModel.setReporterName(data.getUser().getName());
            incidentModels.add(incidentModel);
        });
        return incidentModels;
    }

    public List<IncidentModel> getAllIncident() {
        List<IncidentEntity> incidentEntities = incidentRepository.findAll();
        List<IncidentModel> incidentModelList = new ArrayList<>();
        incidentEntities.forEach(data->{
            IncidentModel incidentModel = new IncidentModel();
            incidentModel.setIncidentId(data.getIncidentId());
            incidentModel.setCustomUniqueIncidentId(data.getCustomUniqueIncidentId());
            incidentModel.setIncidentDetails(data.getIncidentDetails());
            incidentModel.setPriority(data.getPriority());
            incidentModel.setUserId(String.valueOf(data.getUser().getUserId()));
            incidentModel.setStatus(data.getStatus());
            incidentModel.setEnterpriseOrGovernment(data.getEnterpriseOrGovernment());
            incidentModel.setReportedDateTime(data.getCustomUniqueIncidentId());
            incidentModel.setReporterName(data.getUser().getName());
            UserModel userModel = new UserModel();
            userModel.setUserId(data.getUser().getUserId());
            userModel.setName(data.getUser().getName());
            userModel.setAddress(data.getUser().getAddress());
            userModel.setCity(data.getUser().getCity());
            userModel.setEmailId(data.getUser().getEmailId());
            userModel.setCountry(data.getUser().getCountry());
            userModel.setPhoneNumber(data.getUser().getPhoneNumber());
            userModel.setPinCode(data.getUser().getPinCode());
            incidentModel.setUser(userModel);
            incidentModelList.add(incidentModel);
        });
        return incidentModelList;
    }

    public List<IncidentModel> getIncidentDetailsByCustomIncidentId(String customIncidentId) {
        List<IncidentEntity> incidentEntityList = em.createNativeQuery("select * from incident_details where custom_unique_incident_id like concat('%', :customIncidentId , '%') ", IncidentEntity.class)
                .setParameter("customIncidentId", customIncidentId).getResultList();
        System.out.println("incidentEntityList.size()====>"+incidentEntityList.size());
        List<IncidentModel> incidentModelList = new ArrayList<>();
        incidentEntityList.forEach(data->{
            IncidentModel incidentModel = new IncidentModel();
            incidentModel.setIncidentId(data.getIncidentId());
            incidentModel.setCustomUniqueIncidentId(data.getCustomUniqueIncidentId());
            incidentModel.setIncidentDetails(data.getIncidentDetails());
            incidentModel.setPriority(data.getPriority());
            incidentModel.setUserId(String.valueOf(data.getUser().getUserId()));
            incidentModel.setStatus(data.getStatus());
            incidentModel.setEnterpriseOrGovernment(data.getEnterpriseOrGovernment());
            incidentModel.setReportedDateTime(data.getCustomUniqueIncidentId());
            incidentModel.setReporterName(data.getUser().getName());
            incidentModelList.add(incidentModel);
        });
        return incidentModelList;
    }
    @Transactional
    public ResponseEntity<?> deleteIncidentDetailsByIncidentId(String incidentId) {
        IncidentEntity incidentEntity = incidentRepository.findByIncidentId(Long.valueOf(incidentId));
        incidentRepository.delete(incidentEntity);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
