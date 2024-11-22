package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a user in the system.
 */

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Nullable
    private Long id;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Training> trainings = new ArrayList<>();

    @Setter
    @Getter
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Getter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Getter
    @Column(nullable = false, unique = true)
    private String email;

    @Nullable
    public Long getId() {
        return id;
    }


    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

}

