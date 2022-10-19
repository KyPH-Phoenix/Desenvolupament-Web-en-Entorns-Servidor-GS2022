package com.liceu.DemoServlets.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/guess")
public class GuessNumber extends HttpServlet {
    static int randomNumber;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        randomNumber = (int) (Math.random() * 10) + 1;
        System.out.println(randomNumber);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/guessform.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int n = Integer.parseInt(req.getParameter("number"));

        if (n == randomNumber) {
            req.setAttribute("state", "winner");
            req.setAttribute("diff", null);
        } else {
            req.setAttribute("state", "looser");
            if (n < randomNumber) req.setAttribute("diff", "El numero es major");
            if (n > randomNumber) req.setAttribute("diff", "El numero es menor");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/guessform.jsp");
        dispatcher.forward(req, resp);
    }
}
