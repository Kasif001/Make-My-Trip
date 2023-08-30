package com.accio.makeMyTrip.Entities;

import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.Enums.ModeOfTp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Routes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    @Enumerated(value = EnumType.STRING)
    private City source;

    @Enumerated(value = EnumType.STRING)
    private City dest;

    private String ListOfStopsInBet;

    @Enumerated(value = EnumType.STRING)
    private ModeOfTp modeOfTp;


    @OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
    private List<Transport> transportList = new ArrayList<>();


}
