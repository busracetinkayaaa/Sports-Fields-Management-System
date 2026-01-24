package com.app.SportsFieldManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name="user_id",unique = true)
    private User user;

    @OneToMany(mappedBy="client")
    private List<Reservation> reservations=new ArrayList<>();
}
