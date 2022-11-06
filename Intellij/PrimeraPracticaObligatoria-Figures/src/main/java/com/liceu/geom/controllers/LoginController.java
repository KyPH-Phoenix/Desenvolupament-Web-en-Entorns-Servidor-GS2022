package com.liceu.geom.controllers;

import com.liceu.geom.model.User;
import com.liceu.geom.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("id") != null) {
            resp.sendRedirect("/figures");
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        HttpSession session = req.getSession();

        if (session.getAttribute("id") != null) {
            resp.sendRedirect("/figures");
            return;
        }

        if (!userName.isEmpty()) {
            User user = userService.createUser(userName);

            session.setAttribute("id", user.getId());
            resp.sendRedirect("/figures");
            return;
        }

        req.setAttribute("message", "El nom d'usuari introduit no es valid.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }
}
