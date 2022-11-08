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

@WebServlet("/borrarFigura")
public class BorrarFiguraController extends HttpServlet {
    FigureService figureService = new FigureService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int figureId = Integer.parseInt(req.getParameter("figureId"));

        Figure figure = figureService.getFigure(figureId);

        String message = "";

        if (figure.getUser().getId() == (int) session.getAttribute("id")) {
            figureService.deleteFigure(figure);

            message = "La figura " + figure.getName() + " s'ha esborrat correctament.";
        } else {
            message = "ERROR: No pots esborrar una figura que no es teva.";
        }

        req.setAttribute("message", message);
        resp.setHeader("Refresh", "3; URL=/veureFigures");

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/message.jsp");
        dispatcher.forward(req, resp);
    }
}
