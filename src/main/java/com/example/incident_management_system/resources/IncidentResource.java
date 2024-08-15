package com.example.incident_management_system.resources;
import com.example.incident_management_system.entities.UserEntity;
import com.example.incident_management_system.models.IncidentModel;
import com.example.incident_management_system.services.IncidentService;
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
@RequestMapping("/incident")
public class IncidentResource {
    @Autowired
    private IncidentService incidentService;
    @PostMapping("/saveIncidentDetails")
    public ResponseEntity<?> saveIncidentDetails(@Valid @RequestBody IncidentModel incidentModel, BindingResult result){
        Map<String, String> errorMap = new HashMap<>();
        if(result.hasErrors()){
            for(FieldError error : result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.FORBIDDEN);
        }else {
            return incidentService.saveIncidentDetails(incidentModel);
        }




    }
    @PutMapping("/editIncidentDetails")
    public ResponseEntity<?> editIncidentDetails(@RequestBody IncidentModel incidentModel){
        return incidentService.editIncidentDetails(incidentModel);
    }
    @GetMapping("/getIncidentByIncidentId/{incidentId}")
    public IncidentModel getIncidentByIncidentId(@PathVariable String incidentId){
        return incidentService.getIncidentByIncidentId(incidentId);
    }
    @GetMapping("/getIncidentsByUserId/{userId}")
    public List<IncidentModel> getIncidentsByUserId(@PathVariable String userId){
        return incidentService.getIncidentsByUserId(userId);
    }
    @GetMapping("/getAllIncident")
    public List<IncidentModel> getAllIncident(){
        return incidentService.getAllIncident();
    }
    @GetMapping("/getIncidentDetailsByCustomIncidentId/{customIncidentId}")
    public List<IncidentModel> getIncidentDetailsByCustomIncidentId(@PathVariable String customIncidentId){
        return incidentService.getIncidentDetailsByCustomIncidentId(customIncidentId);
    }

    @DeleteMapping("/deleteIncidentDetailsByIncidentId/{incidentId}")
    public ResponseEntity<?> deleteIncidentDetailsByIncidentId(@PathVariable String incidentId){
        return incidentService.deleteIncidentDetailsByIncidentId(incidentId);
    }

}
