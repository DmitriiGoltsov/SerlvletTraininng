package com.dmdev.http.servlet;

import com.dmdev.http.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

    public static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute(USER);

        // If a user is null, we create it and put to map. UserDto data is for an example.
        if (Objects.isNull(user)) {
            user = UserDto.builder()
                    .id(25L)
                    .email("somemail@ya.ru")
                    .build();
            session.setAttribute(USER, user);
        }

    }
}
