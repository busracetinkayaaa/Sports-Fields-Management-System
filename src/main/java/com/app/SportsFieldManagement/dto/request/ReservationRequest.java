package com.app.SportsFieldManagement.dto.request;

import java.time.LocalDateTime;

public record ReservationRequest(Long fieldId,Long clientId, LocalDateTime startHour, LocalDateTime endHour) {
}
