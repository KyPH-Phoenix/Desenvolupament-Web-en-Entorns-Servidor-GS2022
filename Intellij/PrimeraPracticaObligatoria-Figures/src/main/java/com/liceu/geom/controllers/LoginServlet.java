package com.liceu.geom.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("user") != null) {
            resp.sendRedirect("/figures");
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/figures");
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }
}
