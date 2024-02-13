package com.dmdev.http.mapper;

import com.dmdev.http.dto.UserDto;
import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.Sex;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto convert(User object) {
        return UserDto.builder()
                .name(object.getName())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .sex(object.getSex())
                .role(object.getRole())
                .image(object.getImage())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
