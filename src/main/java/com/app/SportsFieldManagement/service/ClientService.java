package com.app.SportsFieldManagement.service;

import com.app.SportsFieldManagement.dto.request.ClientRequest;
import com.app.SportsFieldManagement.dto.response.ClientResponse;
import com.app.SportsFieldManagement.mapper.ClientMapper;
import com.app.SportsFieldManagement.model.Client;
import com.app.SportsFieldManagement.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    public ClientResponse createClient(ClientRequest request){
        log.info("Creating new client: {}", request.name());
        Client client = clientMapper.toEntity(request);
        Client savedClient= clientRepository.save(client);
        return clientMapper.toResponse(savedClient);
    }
    public ClientResponse getClientById(Long clientId){
       return clientRepository.findById(clientId).map(clientMapper::toResponse)
               .orElseThrow(()->new RuntimeException("Client not found with ID:"+clientId));
    }
    public ClientResponse getClientByUserId(Long userId){
        return clientRepository.findByUserId(userId).map(clientMapper::toResponse)
                .orElseThrow(()->new RuntimeException("Client not found for user id {}"+userId));
    }
    @Transactional
    public ClientResponse updateClient(Long clientId,ClientRequest request){
        Client existing=clientRepository.findById(clientId)
                .orElseThrow(()->new RuntimeException("Client not found with {}"+clientId));
        existing.setName(request.name());
        existing.setEmail(request.email());
        existing.setPhone(request.phone());

        log.info("Updating client profile. ID {}",clientId);
        return clientMapper.toResponse(clientRepository.save(existing));
    }
    public List<ClientResponse> getAllClients(){
        return clientRepository.findAll()
                .stream().map(clientMapper::toResponse).toList();
    }
    public void deleteClient(Long id){
        if(!clientRepository.existsById(id)){
            throw new RuntimeException("Client not found ID:"+id);
        }
        log.warn("Deleting client ID:{}",id);
        clientRepository.deleteById(id);
    }

}
