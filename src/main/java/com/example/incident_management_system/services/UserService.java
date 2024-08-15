package com.example.incident_management_system.services;

import com.example.incident_management_system.entities.UserEntity;
import com.example.incident_management_system.models.UserModel;
import com.example.incident_management_system.repositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public ResponseEntity<?> saveUserDetails(UserModel userModel) {
        if (userRepository.existsByEmailId(userModel.getEmailId())) {
            return new ResponseEntity<>("Email ID already exists", HttpStatus.CONFLICT);
        }
        UserEntity user = new UserEntity();
        user.setName(userModel.getName());
        user.setAddress(userModel.getAddress());
        user.setPhoneNumber(userModel.getPhoneNumber());
        user.setPinCode(userModel.getPinCode());
        user.setEmailId(userModel.getEmailId());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setCreationDate(new Date());
        try {
            String apiUrl = "https://nominatim.openstreetmap.org/search?postalcode=" + userModel.getPinCode() + "&format=json&countrycodes=in";
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);
            JsonNode locationData;
            if(response.size()>0){
                locationData= response.get(0);
            }else {
                return new ResponseEntity<>("Address not found.", HttpStatus.FORBIDDEN);
            }
            String displayName = locationData.path("display_name").asText();
            String[] locationParts = displayName.split(", ");
            String city = locationParts[locationParts.length - 3];
            String country = locationParts[locationParts.length - 1];
            System.out.println("city===>" + city);
            System.out.println("country=====>" + country);
            user.setCity(city);
            user.setCountry(country);
            userRepository.save(user);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed", HttpStatus.FORBIDDEN);
        }

    }

    public List<UserModel> getAllUserDetails() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        userEntityList.forEach(user->{
            UserModel model = new UserModel();
            model.setUserId(user.getUserId());
            model.setName(user.getName());
            model.setEmailId(user.getEmailId());
            model.setCreationDate(format.format(user.getCreationDate()));
            model.setAddress(user.getAddress());
            model.setCity(user.getCity());
            model.setCountry(user.getCountry());
            model.setPhoneNumber(user.getPhoneNumber());
            model.setPinCode(user.getPinCode());
            userModels.add(model);
        });
        return userModels;
    }

    public UserModel getUserDetailsByUserId(String userId) {
        UserEntity user = userRepository.findByUserId(Long.valueOf(userId));
        UserModel model = new UserModel();
        model.setUserId(user.getUserId());
        model.setName(user.getName());
        model.setAddress(user.getAddress());
        model.setCity(user.getCity());
        model.setEmailId(user.getEmailId());
        model.setCountry(user.getCountry());
        model.setCreationDate(format.format(user.getCreationDate()));
        model.setPhoneNumber(user.getPhoneNumber());
        model.setPinCode(user.getPinCode());
        return model;
    }
    @Transactional
    public ResponseEntity<?> updateUserDetails(UserModel userModel) {
        UserEntity entity = userRepository.findByUserId(userModel.getUserId());
        entity.setName(userModel.getName());
        entity.setAddress(userModel.getAddress());
        entity.setPhoneNumber(userModel.getPhoneNumber());
        entity.setPinCode(userModel.getPinCode());
        entity.setCreationDate(new Date());
        try {
            String apiUrl = "https://nominatim.openstreetmap.org/search?postalcode=" + userModel.getPinCode() + "&format=json&countrycodes=in";
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);
            JsonNode locationData;
            if(response.size()>0){
                locationData= response.get(0);
            }else {
                return new ResponseEntity<>("Address not found.", HttpStatus.FORBIDDEN);
            }
            String displayName = locationData.path("display_name").asText();
            String[] locationParts = displayName.split(", ");
            String city = locationParts[locationParts.length - 3];
            String country = locationParts[locationParts.length - 1];
            System.out.println("city===>" + city);
            System.out.println("country=====>" + country);
            entity.setCity(city);
            entity.setCountry(country);
            userRepository.save(entity);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed", HttpStatus.FORBIDDEN);
        }
    }
    @Transactional
    public ResponseEntity<?> deleteUserDetailsByUserId(String userId) {
        UserEntity user = userRepository.findByUserId(Long.valueOf(userId));
        userRepository.delete(user);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    public UserModel getCityAndCountryByPinCode(String pinCode) {
        UserModel user = new UserModel();
        try {
            String apiUrl = "https://nominatim.openstreetmap.org/search?postalcode=" + pinCode + "&format=json&countrycodes=in";
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);
            JsonNode locationData;
            if(response.size()>0){
                locationData= response.get(0);
            }else {
                return user;
            }
            String displayName = locationData.path("display_name").asText();
            String[] locationParts = displayName.split(", ");
            String city = locationParts[locationParts.length - 3];
            String country = locationParts[locationParts.length - 1];
            System.out.println("city===>" + city);
            System.out.println("country=====>" + country);
            user.setCity(city);
            user.setCountry(country);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return user;
        }
    }

}
