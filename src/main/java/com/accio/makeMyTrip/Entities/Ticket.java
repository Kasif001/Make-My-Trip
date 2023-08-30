package com.accio.makeMyTrip.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private String routeDetails;
    private LocalDate journeyDate;
    private LocalTime journeyTime;
    private Integer totalCost;
    private String seatNo;
    private Integer noOfPeopleCanTravel;



   @OneToOne
   @JsonIgnore
    @JoinColumn
    private Booking booking;


}
