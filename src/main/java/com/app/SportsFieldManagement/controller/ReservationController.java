package com.app.SportsFieldManagement.controller;

import com.app.SportsFieldManagement.dto.request.ReservationRequest;
import com.app.SportsFieldManagement.dto.response.ReservationResponse;
import com.app.SportsFieldManagement.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/admin/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request){
        ReservationResponse response=reservationService.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations(){
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByField(@PathVariable Long fieldId){
        return ResponseEntity.ok(reservationService.getReservationsByField(fieldId));
    }
    @GetMapping("/client")
    public ResponseEntity<List<ReservationResponse>> getReservationsByClient(@RequestParam String username){
        return ResponseEntity.ok(reservationService.getReservationsByClient(username));
    }
    @GetMapping("/field-and-date")
    public ResponseEntity<List<ReservationResponse>> getReservationsByFieldAndDate(@RequestParam Long fieldId,
                                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        return ResponseEntity.ok(reservationService.getReservationsByFieldAndDate(fieldId,date));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id){
        reservationService.cancelReservations(id);
        return ResponseEntity.noContent().build();
    }

}
