package com.app.SportsFieldManagement.repository;

import com.app.SportsFieldManagement.model.Client;
import com.app.SportsFieldManagement.model.Reservation;
import com.app.SportsFieldManagement.model.SportField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAll();
    @Query("SELECT r FROM Reservation r WHERE r.field.id=:fieldId AND DATE(r.startHour)=:date")
    List<Reservation> findByFieldIdAndDate(@Param("fieldId") Long fieldId,@Param("date")LocalDate date);
    @Query("SELECT case when count(r)>0 then true else false end FROM Reservation r WHERE r.field= :field AND r.endHour>:startHour AND r.startHour<:endHour")
    boolean existsConflictingReservations(@Param("field")SportField field,
                                                  @Param("startHour") LocalDateTime startHour, @Param("endHour") LocalDateTime endHour);
   List<Reservation> findByFieldId(Long fieldId);
   List<Reservation>findByClientName(String name);

}
