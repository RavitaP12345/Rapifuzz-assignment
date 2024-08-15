package com.example.incident_management_system.resources;

import com.example.incident_management_system.models.IncidentModel;
import com.example.incident_management_system.models.UserModel;
import com.example.incident_management_system.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private UserService userService;
    @PostMapping("/saveUserDetails")
    public ResponseEntity<?> saveUserDetails(@Valid @RequestBody UserModel userModel, BindingResult result){
        Map<String, String> errorMap = new HashMap<>();
        if(result.hasErrors()){
            for(FieldError error : result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.FORBIDDEN);
        }else {
            return userService.saveUserDetails(userModel);
        }
    }

    @GetMapping("/getAllUserDetails")
    public List<UserModel> getAllUserDetails(){
        return userService.getAllUserDetails();
    }

    @GetMapping("/getUserDetailsByUserId/{userId}")
    public UserModel getUserDetailsByUserId(@PathVariable String userId){
        return userService.getUserDetailsByUserId(userId);
    }


    @PutMapping("/updateUserDetails")
    public ResponseEntity<?> updateUserDetails(@RequestBody UserModel userModel){
        if(userModel.getUserId()==null){
            return new ResponseEntity<>("User id is null", HttpStatus.FORBIDDEN);
        }
        return userService.updateUserDetails(userModel);
    }

    @DeleteMapping("/deleteUserDetailsByUserId/{userId}")
    public ResponseEntity<?> deleteUserDetailsByUserId(@PathVariable String userId){
        return  userService.deleteUserDetailsByUserId(userId);
    }

    @GetMapping("/getCityAndCountryByPinCode/{pinCode}")
    public UserModel getCityAndCountryByPinCode(@PathVariable String pinCode){
        return  userService.getCityAndCountryByPinCode(pinCode);
    }





}
