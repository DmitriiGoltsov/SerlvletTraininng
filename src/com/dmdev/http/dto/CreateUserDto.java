package com.dmdev.http.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {

    String name;
    String birthday;
    String email;
    String password;
    String role;
    String sex;
    Part image;
}
