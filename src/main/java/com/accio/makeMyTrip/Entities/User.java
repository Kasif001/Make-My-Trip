package com.accio.makeMyTrip.Entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;
    private Integer age;
    private String gender;
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Booking> bookingList2 = new ArrayList<>();
}
