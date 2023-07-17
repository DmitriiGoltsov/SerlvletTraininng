package com.goltsov.http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String paramValue = req.getParameter("param");
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println();

        resp.setContentType("text/html");
        resp.setHeader("token", "12345");
        // resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try(Writer writer = resp.getWriter()) {
            writer.write("Hello from First Servlet!");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*var parameterMap = req.getParameterMap();
        System.out.println(parameterMap);*/

        try (BufferedReader reader = req.getReader();
            Stream<String> lines = reader.lines()) {

            lines.forEach(System.out::println);

        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
