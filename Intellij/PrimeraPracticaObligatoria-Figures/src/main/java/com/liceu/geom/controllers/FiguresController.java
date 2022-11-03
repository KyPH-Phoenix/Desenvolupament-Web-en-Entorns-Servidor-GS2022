package com.liceu.geom.controllers;

import com.liceu.geom.model.Figure;
import com.liceu.geom.service.FigureService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/figures")
public class FiguresController extends HttpServlet {
    FigureService figureService = new FigureService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/figures.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute("id");
        String figureName = req.getParameter("figureName");
        int xCord = Integer.parseInt(req.getParameter("xCord"));
        int yCord = Integer.parseInt(req.getParameter("yCord"));
        int size = Integer.parseInt(req.getParameter("size"));
        String shape = req.getParameter("shape");
        String color = req.getParameter("color");

        figureService.createFigure(userId, figureName, xCord, yCord, size, shape, color);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/figures.jsp");
        dispatcher.forward(req, resp);
    }
}
