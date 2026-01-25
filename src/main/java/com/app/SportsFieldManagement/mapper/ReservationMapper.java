package com.app.SportsFieldManagement.mapper;

import com.app.SportsFieldManagement.dto.response.ReservationResponse;
import com.app.SportsFieldManagement.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public ReservationResponse toResponse(Reservation reservation){
        return new ReservationResponse(
                reservation.getId(),
                reservation.getField().getName(),
                reservation.getField().getSportType().name(),
                reservation.getStartHour(),
                reservation.getEndHour(),
                reservation.getField().isIndoor(),
                reservation.durationInMins(),
                reservation.getClient().getName()
        );
    }
}
