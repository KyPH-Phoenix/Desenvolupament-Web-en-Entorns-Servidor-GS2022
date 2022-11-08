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
import java.util.List;

@WebServlet("/mevesFigures")
public class MevesFiguresController extends HttpServlet {
    FigureService figureService = new FigureService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("id");

        List<Figure> figureList = figureService.getAllFiguresFromUser(userId);
        req.setAttribute("figures", figureList);
        req.setAttribute("pageName", "Les Meves Figures");

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/veureFigures.jsp");
        dispatcher.forward(req, resp);
    }
}
