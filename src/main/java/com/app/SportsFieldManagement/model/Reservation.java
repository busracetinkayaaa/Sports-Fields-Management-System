package com.app.SportsFieldManagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Duration;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startHour;
    private LocalDateTime endHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="field_id")
    private SportField field;

    public long durationInMins(){
        return Duration.between(startHour,endHour).toMinutes();
    }
    public boolean overlaps(LocalDateTime start, LocalDateTime end){
        return start.isBefore(this.endHour)&& end.isAfter(this.startHour);
    }
    public void validate(){
        if (startHour.isAfter(endHour)) {
            throw new IllegalStateException("Start must be before end");
        }
    }

}
