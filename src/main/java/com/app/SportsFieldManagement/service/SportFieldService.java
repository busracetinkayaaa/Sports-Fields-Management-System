package com.app.SportsFieldManagement.service;

import com.app.SportsFieldManagement.dto.request.SportFieldRequest;
import com.app.SportsFieldManagement.dto.response.SportFieldResponse;
import com.app.SportsFieldManagement.mapper.SportFieldMapper;
import com.app.SportsFieldManagement.model.SportField;
import com.app.SportsFieldManagement.repository.SportFieldRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportFieldService {
    private final SportFieldRepository sportFieldRepository;
    private final SportFieldMapper sportFieldMapper;
    public SportFieldResponse addField(SportFieldRequest request){
        SportField field=new SportField();
        field.setName(request.name());
        field.setSportType(request.sportType());
        field.setPrice(request.price());
        field.setIndoor(request.indoor());
        field.setLatitude(request.latitude());
        field.setLongitude(request.longitude());

        log.info("Adding new field:{}",request.name());
        SportField savedField = sportFieldRepository.save(field);

        return sportFieldMapper.toResponse(savedField);
    }
    public List<SportFieldResponse> getAllFields(){
        return sportFieldRepository.findAll()
                .stream().map(sportFieldMapper::toResponse).toList();
    }
    public SportFieldResponse getFieldById(Long id){
        return sportFieldRepository.findById(id).map(sportFieldMapper::toResponse)
                .orElseThrow(()-> new RuntimeException("Field not found. ID:"+id));
    }
    @Transactional
    public SportFieldResponse editField(Long id,SportFieldRequest request){
        log.info("Updating field ID:{} ",id);
        SportField existing_field = sportFieldRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Sport field not found"));
        existing_field.setName(request.name());
        existing_field.setPrice(request.price());
        existing_field.setSportType(request.sportType());
        existing_field.setIndoor(request.indoor());

        return sportFieldMapper.toResponse(sportFieldRepository.save(existing_field));
    }
    public void deleteField(Long id){
        if(!sportFieldRepository.existsById(id)){
            throw new RuntimeException("The field needs to be deleted not found");
        }
        log.info("Deleting ID:{} field",id);
        sportFieldRepository.deleteById(id);
    }
}
