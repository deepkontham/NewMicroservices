package com.microservices.cart.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;
    @Size(min = 3,max = 30,message = "Invalid name !!")
    private String name;
    
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "Invalid user email !!")
//    @Email(message = "Invalid User Email !!")
    @NotBlank(message = "Email is required !!")
    private String email;
    @NotBlank(message = "Password is required !!")
    private String password;
    @Size(min = 10,max = 10,message = "Enter valid phone number !!")
    private String phone;
    @Size(min = 4,max = 6,message = "Invalid gender !!")
    private String gender;
    @NotBlank(message = "write something about yourself !!")
    private String about;

  
}
