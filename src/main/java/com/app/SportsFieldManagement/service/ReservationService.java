package com.app.SportsFieldManagement.service;

import com.app.SportsFieldManagement.dto.request.ReservationRequest;
import com.app.SportsFieldManagement.dto.response.ReservationResponse;
import com.app.SportsFieldManagement.mapper.ReservationMapper;
import com.app.SportsFieldManagement.model.Client;
import com.app.SportsFieldManagement.model.Reservation;
import com.app.SportsFieldManagement.model.SportField;
import com.app.SportsFieldManagement.repository.ClientRepository;
import com.app.SportsFieldManagement.repository.ReservationRepository;
import com.app.SportsFieldManagement.repository.SportFieldRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final SportFieldRepository sportFieldRepository;
    private final ReservationMapper reservationMapper;
    private final WeatherService weatherService;
    @Transactional
    public ReservationResponse createReservation(ReservationRequest request ){
        log.info("Admin is creating a reservation for fieldId={}, start={}, end={}",
                request.fieldId(), request.startHour(), request.endHour());
        SportField field= sportFieldRepository.findById(request.fieldId()).orElseThrow(()->new RuntimeException("Field not found"));
        Client client=clientRepository.findById(request.clientId()).orElseThrow(()-> new RuntimeException("Client not found"));

        if(!field.isIndoor()){
            boolean suitableTemp= weatherService.isWeatherSuitableForOutdoor(field.getLatitude(),field.getLongitude());
            if(!suitableTemp){
                throw new IllegalStateException("Outdoor fields can only be reserved if temperature is above 10°C");
            }
        }

        boolean conflict=reservationRepository.existsConflictingReservations(field,request.startHour(),request.endHour());
        if(conflict){
            throw new IllegalStateException("Field is not available for this time range");
        }
        Reservation newReservation= new Reservation();

        newReservation.setClient(client);
        newReservation.setField(field);
        newReservation.setStartHour(request.startHour());
        newReservation.setEndHour(request.endHour());
        newReservation.validate();
        Reservation savedReservation = reservationRepository.save(newReservation);

        log.info("Reservation created. id={}", newReservation.getId());
        return reservationMapper.toResponse(savedReservation);
    }
    public List<ReservationResponse> getReservationsByField(Long fieldId){
        return reservationRepository.findByFieldId(fieldId).stream()
                .map(reservationMapper::toResponse).toList();
    }
    public List<ReservationResponse> getReservationsByClient(String name){
        return reservationRepository.findByClientName(name).stream()
                .map(reservationMapper::toResponse).toList();
    }
    public List<ReservationResponse> getReservationsByFieldAndDate(Long fieldId, LocalDate date){
        return reservationRepository.findByFieldIdAndDate(fieldId,date).stream()
                .map(reservationMapper::toResponse).toList();
    }
    public List<ReservationResponse> getAllReservations(){
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toResponse).toList();
    }
    public void cancelReservations(Long reservationId){
        if(!reservationRepository.existsById(reservationId)){
            throw new IllegalStateException("Reservation not found"+reservationId);
        }
        reservationRepository.deleteById(reservationId);
        log.info("Reservation cancelled:{}",reservationId);
    }

}
