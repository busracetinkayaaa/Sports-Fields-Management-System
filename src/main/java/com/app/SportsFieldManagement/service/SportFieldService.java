package com.app.SportsFieldManagement.service;

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
    public SportField addField(SportField sportField){
        log.info("Adding new field:{}",sportField.getName());
        return sportFieldRepository.save(sportField);
    }
    public List<SportField> getAllFields(){
        return sportFieldRepository.findAll();
    }
    public SportField getFieldById(Long id){
        return sportFieldRepository.findById(id).orElseThrow(()-> new RuntimeException("Field not found. ID:"+id));
    }
    @Transactional
    public SportField editField(Long id,SportField field){
        log.info("Updating field ID:{} ",id);
        SportField existing_field = getFieldById(id);
        existing_field.setName(field.getName());
        existing_field.setPrice(field.getPrice());
        existing_field.setSportType(field.getSportType());

        return existing_field;
    }
    public void deleteField(Long id){
        if(!sportFieldRepository.existsById(id)){
            throw new RuntimeException("The field needs to be deleted not found");
        }
        log.info("Deleting ID:{} field",id);
        sportFieldRepository.deleteById(id);
    }
}
