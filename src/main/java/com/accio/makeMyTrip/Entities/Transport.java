package com.accio.makeMyTrip.Entities;


import com.accio.makeMyTrip.Enums.ModeOfTp;
import com.accio.makeMyTrip.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Transport")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transId;

    @Enumerated(value = EnumType.STRING)
    private ModeOfTp modeOfTp;

    private LocalDate journeyDate;
    private LocalTime startTime;
    private double journeyTime;
    private String companyName;

    @ManyToOne
    @JoinColumn
    private Route route;


    @OneToMany(mappedBy = "transport",cascade = CascadeType.ALL)
    List<Seat> seatList = new ArrayList<>();


    @OneToMany(mappedBy = "transport321",cascade = CascadeType.ALL)
    List<Booking> bookingList = new ArrayList<>();

}
