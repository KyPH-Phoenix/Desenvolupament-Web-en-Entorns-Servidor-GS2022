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

@WebServlet("/figures")
public class FiguresController extends HttpServlet {
    FigureService figureService = new FigureService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String figureId = req.getParameter("figureId");

        if (figureId != null) {
            Figure figure = figureService.getFigure(Integer.parseInt(figureId));
            req.setAttribute("figure", figure);
        }

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

        if (figureName.isEmpty()) {
            System.out.println(figureName);
            do {
                int random = (int) (Math.random() * 10000);
                figureName = shape + "_" + random;
            } while (nameAlreadyExists(figureName, (int) session.getAttribute("id")));
        }

        figureService.createFigure(userId, figureName, xCord, yCord, size, shape, color);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/figures.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean nameAlreadyExists(String figureName, int id) {
        List<Figure> figureList = figureService.getAllFiguresFromUser(id);

        return figureList
                .stream()
                .anyMatch(
                        figure -> figure.getName().equals(figureName))
                ;
    }
}
