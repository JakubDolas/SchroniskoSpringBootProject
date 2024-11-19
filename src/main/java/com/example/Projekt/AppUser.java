package com.example.Projekt;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class AppUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String password;
    private String role;
    private Date createdAt;
}
