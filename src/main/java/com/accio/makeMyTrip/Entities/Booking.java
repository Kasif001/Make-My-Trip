package com.accio.makeMyTrip.Entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Booking")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingId;

    private String seatNos;
    private LocalDate journeyDate;

    @ManyToOne
    @JoinColumn
    private Transport transport321;

    @ManyToOne
    @JoinColumn
    private User user;


    @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
    private Ticket ticket;


}
