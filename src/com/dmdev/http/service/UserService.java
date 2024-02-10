package com.dmdev.http.service;

import com.dmdev.http.dao.UserDao;
import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.User;
import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.validation.CreateUserValidator;

import com.dmdev.http.validation.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();

    private final UserDao userDao = UserDao.getInstance();

    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Long create(CreateUserDto createUserDto) {
        // validation
        // Mapping to entity
        // UserDao.save and returning id

        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        User user = createUserMapper.convert(createUserDto);
        return userDao.save(user).getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
