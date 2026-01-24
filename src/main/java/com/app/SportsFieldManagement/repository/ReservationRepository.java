package com.app.SportsFieldManagement.repository;

import com.app.SportsFieldManagement.model.Reservation;
import com.app.SportsFieldManagement.model.SportField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> getReservationsByDate(LocalDateTime startHour);
    List<Reservation> findAll();
    @Query("SELECT r FROM Reservation r WHERE r.field= :field AND r.endHour>:start AND r.startHour<:end")
    List<Reservation> findConflictingReservations(@Param("field")SportField field,
                                                  @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
