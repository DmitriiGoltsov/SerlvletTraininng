package com.dmdev.http.service;

import com.dmdev.http.dao.UserDao;
import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.dto.UserDto;
import com.dmdev.http.entity.User;
import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.mapper.UserMapper;
import com.dmdev.http.validation.CreateUserValidator;

import com.dmdev.http.validation.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();

    private final UserDao userDao = UserDao.getInstance();

    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    private final ImageService imageService = ImageService.getInstance();

    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByLoginAndPassword(email, password)
                .map(userMapper::convert);
    }

    @SneakyThrows
    public Long create(CreateUserDto createUserDto) {
        // validation
        // Mapping to entity
        // UserDao.save and returning id

        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        User user = createUserMapper.convert(createUserDto);
        imageService.upload(user.getImage(), createUserDto.getImage().getInputStream());

        return userDao.save(user).getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
