package com.example.crud.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String location;

    @Column(unique = true)
    private String nif;

    public User(RequestUser data) {
        this.name = data.name();
        this.location = data.location();
        this.nif = data.nif();
    }
}
