package com.app.SportsFieldManagement.dto.response;

import java.time.LocalDateTime;

public record ReservationResponse (Long id, String fieldName, String fieldType,
                                   LocalDateTime startHour, LocalDateTime endHour,
                                   boolean isIndoor,long durationInMinutes,String clientName) {
}
