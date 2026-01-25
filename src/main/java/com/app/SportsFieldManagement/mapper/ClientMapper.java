package com.app.SportsFieldManagement.mapper;

import com.app.SportsFieldManagement.dto.request.ClientRequest;
import com.app.SportsFieldManagement.dto.response.ClientResponse;
import com.app.SportsFieldManagement.model.Client;
import com.app.SportsFieldManagement.model.User;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientResponse toResponse(Client client){
        return new ClientResponse(client.getId(), client.getName(), client.getPhone(), client.getEmail(),
                client.getUser().getUsername());
    }
    public Client toEntity(ClientRequest request, User user){
        return Client.builder().name(request.name())
                .phone(request.phone())
                .email(request.email())
                .user(user)
                .build();
    }
}
