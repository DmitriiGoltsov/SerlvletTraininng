package com.dmdev.http.dao;

import com.dmdev.http.entity.User;
import com.dmdev.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.*;

public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    public static final String SAVE_USER_SQL =
            "INSERT INTO users (name, birthday, email, password, role, sex) VALUES (?, ?, ?, ?, ?, ?)";

    private UserDao() {
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_SQL, RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getSex().name());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            entity.setId(resultSet.getLong("id"));

            return entity;
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
