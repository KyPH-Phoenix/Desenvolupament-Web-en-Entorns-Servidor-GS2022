package com.liceu.DemoServlets.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/guess2")
public class GuessNumber2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int randomNumber = (int) (Math.random() * 10) + 1;

        Cookie cookie = new Cookie("secretNumber", String.valueOf(randomNumber));
        resp.addCookie(cookie);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/guessform2.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int n = Integer.parseInt(req.getParameter("number"));
        int randomNumber = 0;

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("secretNumber")) {
                randomNumber = Integer.parseInt(cookie.getValue());
            }
        }

        if (n == randomNumber) {
            req.setAttribute("state", "winner");
            req.setAttribute("diff", null);
        } else {
            req.setAttribute("state", "looser");
            if (n < randomNumber) req.setAttribute("diff", "El numero es major");
            if (n > randomNumber) req.setAttribute("diff", "El numero es menor");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/guessform2.jsp");
        dispatcher.forward(req, resp);
    }
}
