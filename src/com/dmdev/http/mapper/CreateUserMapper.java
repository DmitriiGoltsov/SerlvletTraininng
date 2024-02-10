package com.dmdev.http.mapper;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.Sex;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.LocalDateFormatter;

import java.time.LocalDate;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User convert(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .sex(Sex.valueOf(object.getSex()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
