package com.example.CarRental.Entity;

import com.example.CarRental.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
