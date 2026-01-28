package com.app.SportsFieldManagement.controller;

import com.app.SportsFieldManagement.dto.request.ClientRequest;
import com.app.SportsFieldManagement.dto.response.ClientResponse;
import com.app.SportsFieldManagement.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/client")
@RequiredArgsConstructor
public class ClientController{
    private final ClientService clientService;
    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(request));
    }
    @GetMapping("/client/{clientId}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long clientId){
        return ResponseEntity.ok(clientService.getClientById(clientId));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ClientResponse> getClientByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(clientService.getClientByUserId(userId));
    }
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }
    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long clientId,@Valid @RequestBody ClientRequest request){
        return ResponseEntity.ok(clientService.updateClient(clientId,request));
    }
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
