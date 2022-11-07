package com.liceu.geom.controllers;

import com.liceu.geom.model.Figure;
import com.liceu.geom.service.FigureService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/veureFigures")
public class VeureFiguresController extends HttpServlet {
    FigureService figureService = new FigureService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Figure> figureList = figureService.getAllFigures();
        req.setAttribute("figures", figureList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/veureFigures.jsp");
        dispatcher.forward(req, resp);
    }
}
