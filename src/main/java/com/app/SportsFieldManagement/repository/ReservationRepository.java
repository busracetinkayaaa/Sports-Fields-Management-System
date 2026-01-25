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
    @Query("SELECT r FROM Reservation r WHERE r.field=:field AND DATE(r.startHour)=:date")
    List<Reservation> findByFieldAndDate(SportField field, LocalDate date);
    @Query("SELECT r FROM Reservation r WHERE r.field= :field AND r.endHour>:start AND r.startHour<:end")
    boolean findConflictingReservations(@Param("field")SportField field,
                                                  @Param("startHour") LocalDateTime startHour, @Param("endHour") LocalDateTime endHour);
   List<Reservation> findByField(SportField field);
   List<Reservation>findByClient(Client client);

}
