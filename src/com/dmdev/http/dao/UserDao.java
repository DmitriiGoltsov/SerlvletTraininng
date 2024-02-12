package com.dmdev.http.dao;

import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.Sex;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.ConnectionManager;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    public static final String SAVE_USER_SQL =
            "INSERT INTO users (name, birthday, email, password, role, sex, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String FIND_LOGIN_PASSWORD_SQL = """
            SELECT * FROM users
            WHERE email = ? AND password = ?
            """;

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

    @SneakyThrows
    public Optional<User> findByLoginAndPassword(String login, String password) {
        try (Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOGIN_PASSWORD_SQL)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return Optional.ofNullable(user);
        }
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
            preparedStatement.setObject(7, entity.getImage());

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

    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .sex(Sex.valueOf(resultSet.getObject("sex", String.class)))
                .image(resultSet.getString("image"))
                .build();
    }
}
