package com.liceu.DemoServlets.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/guess3")
public class GuessNumberBuena extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int randomNumber = (int) (Math.random() * 10) + 1;

        HttpSession session = req.getSession();
        session.setAttribute("randomnumber", randomNumber);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/guessformBueno.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int n = Integer.parseInt(req.getParameter("number"));
        int randomNumber = (int) req.getSession().getAttribute("randomnumber");

        if (n == randomNumber) {
            req.setAttribute("state", "winner");
            req.setAttribute("diff", null);
        } else {
            req.setAttribute("state", "looser");
            if (n < randomNumber) req.setAttribute("diff", "El numero es major");
            if (n > randomNumber) req.setAttribute("diff", "El numero es menor");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/guessformBueno.jsp");
        dispatcher.forward(req, resp);
    }
}
