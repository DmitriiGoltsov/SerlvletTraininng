package com.dmdev.http.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;

    private String name;

    private LocalDate birthday;

    private String email;

    private String password;

    private Role role;

    private Sex sex;

    private String image;
}
