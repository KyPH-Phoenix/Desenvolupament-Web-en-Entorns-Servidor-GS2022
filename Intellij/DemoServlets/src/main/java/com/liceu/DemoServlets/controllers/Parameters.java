package com.liceu.DemoServlets.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pars")
public class Parameters extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        req.setAttribute("nom", nom);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/form.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int par1 = Integer.parseInt(req.getParameter("a"));
        int par2 = Integer.parseInt(req.getParameter("b"));
        int operator = Integer.parseInt(req.getParameter("operation"));

        System.out.println(operator);
        int r = 0;

        if (operator == 1) {
            r = par1 + par2;
        } else if (operator == 2) {
            r = par1 - par2;
        }

        req.setAttribute("result", r);
        req.setAttribute("a", par1);
        req.setAttribute("b", par2);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/form.jsp");
        dispatcher.forward(req, resp);
    }
}
