package com.app.SportsFieldManagement.controller;

import com.app.SportsFieldManagement.dto.request.SportFieldRequest;
import com.app.SportsFieldManagement.dto.response.SportFieldResponse;
import com.app.SportsFieldManagement.service.SportFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/admin/sport-field")
@RequiredArgsConstructor
public class SportFieldController {
    private final SportFieldService sportFieldService;
    @PostMapping
    public ResponseEntity<SportFieldResponse> addField(@RequestBody SportFieldRequest request){
        SportFieldResponse response=sportFieldService.addField(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<SportFieldResponse>> getAllFields(){
        return ResponseEntity.ok(sportFieldService.getAllFields());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SportFieldResponse> getFieldById(@PathVariable Long id){
        return ResponseEntity.ok(sportFieldService.getFieldById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SportFieldResponse> editField(@PathVariable Long id,@RequestBody SportFieldRequest request){
        return ResponseEntity.ok(sportFieldService.editField(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteField(@PathVariable Long id){
        sportFieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }
}
