package com.app.SportsFieldManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Builder
public class SportField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public enum FieldType{SOCCER,BASKETBALL,TENNIS};
    private FieldType sportType;
    private boolean isIndoor;
    private double latitude;
    private double longitude;
    private BigDecimal price;

    @OneToMany(mappedBy = "field",fetch = FetchType.LAZY)
    @Builder.Default
    private List<Reservation> reservation=new ArrayList<>();

}
