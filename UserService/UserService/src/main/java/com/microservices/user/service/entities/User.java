package com.microservices.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_password", length = 100)
    private String password;

    @Column(name = "phone", length = 10)
    private String phone;

    private String gender;

    @Column(length = 1000)
    private String about;

    @Column(name = "user_image_name")
    private String imageName;
}
