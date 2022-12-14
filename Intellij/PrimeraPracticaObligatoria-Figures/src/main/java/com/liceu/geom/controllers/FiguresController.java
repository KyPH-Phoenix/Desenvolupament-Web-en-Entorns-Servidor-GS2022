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
import java.time.LocalDate;
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

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/visualitzador.jsp");
            dispatcher.forward(req, resp);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/figures.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (!validData(req)) {
            req.setAttribute("message", "ERROR: S'han modificat algunes dades i no son valides.");
            resp.setHeader("Refresh", "3; URL=/figures");

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/message.jsp");
            dispatcher.forward(req, resp);
        }

        int userId = (int) session.getAttribute("id");
        String figureName = req.getParameter("figureName");
        int xCord = Integer.parseInt(req.getParameter("xCord"));
        int yCord = Integer.parseInt(req.getParameter("yCord"));
        int size = Integer.parseInt(req.getParameter("size"));
        String shape = req.getParameter("shape");
        String color = req.getParameter("color");
        LocalDate date = LocalDate.now();

        String message = "Figura creada correctament.";

        if (figureName.isEmpty()) {
            do {
                int random = (int) (Math.random() * 10000);
                figureName = shape + "_" + random;
            } while (nameAlreadyExists(figureName, (int) session.getAttribute("id")));

            message += " Se li ha autogenerat el nom " + figureName + ".";
            figureService.createFigure(userId, figureName, xCord, yCord, size, shape, color, date);

        } else if (nameAlreadyExists(figureName, (int) session.getAttribute("id"))) {
            message = "ERROR: El nom de la figura ja existeix. La figura no s'ha creat.";

        } else {
            figureService.createFigure(userId, figureName, xCord, yCord, size, shape, color, date);
        }

        req.setAttribute("message", message);
        resp.setHeader("Refresh", "3; URL=/figures");

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/message.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean validData(HttpServletRequest req) {
        String xCord = req.getParameter("xCord");
        String yCord = req.getParameter("yCord");
        String size = req.getParameter("size");
        try {
            Integer.parseInt(xCord);
            Integer.parseInt(yCord);
            Integer.parseInt(size);
        } catch (NumberFormatException e) {
            return false;
        }

        String shape = req.getParameter("shape");
        String color = req.getParameter("color");

        return (validShape(shape) && validColor(color));
    }

    private boolean validColor(String color) {
        List<String> shapes = List.of("black", "green", "red", "blue", "yellow", "gris");

        return shapes
                .stream()
                .anyMatch(c -> c.equals(color));
    }

    private boolean validShape(String shape) {
        List<String> shapes = List.of("circle", "square", "triangle", "pentagon", "star");

        return shapes
                .stream()
                .anyMatch(s -> s.equals(shape));
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
