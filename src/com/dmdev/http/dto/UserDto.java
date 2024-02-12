package com.dmdev.http.dto;

import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.Sex;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {

    Long id;

    String name;

    LocalDate birthday;

    String email;

    String image;

    Role role;

    Sex sex;
}
