package com.accio.makeMyTrip.Entities;

import com.accio.makeMyTrip.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seatId;

    private String seatNo;//1E,2B,3C

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private Integer price;

    @ManyToOne
    @JoinColumn
    private Transport transport;


}
