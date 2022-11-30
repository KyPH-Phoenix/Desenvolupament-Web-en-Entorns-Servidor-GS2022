package com.liceu.maze.controller;

import com.liceu.maze.service.DataBaseService;
import com.liceu.maze.service.MazeService;
import com.liceu.maze.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@WebServlet("/endform")
public class EndController extends HttpServlet {
    DataBaseService dataBaseService = new DataBaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        long startTime = (long) session.getAttribute("startTime");
        long timeExpent = new Date().getTime() - startTime;

        String mapName = "Mapa" + (int) session.getAttribute("mapId");

        req.setAttribute("time", Util.timeToString(timeExpent));
        req.setAttribute("mapName", mapName);

        session.removeAttribute("startTime");
        session.setAttribute("timeExpent", timeExpent);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/endform.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Dentro del post");
        HttpSession session = req.getSession();

        String userName = (String) session.getAttribute("userName");
        String mapName = "Mapa" + (int) session.getAttribute("mapId");
        long timeSpent = (long) session.getAttribute("timeExpent");

        try {
            System.out.println("*Se inserta el user*");
            dataBaseService.registerWinner(userName, mapName, timeSpent);
        } catch (RuntimeException e) {
            System.out.println("A LA MIERDA");
        }

        session.removeAttribute("userName");
        session.removeAttribute("mapId");
        session.removeAttribute("timeExpent");
        session.removeAttribute("gameWon");

        resp.sendRedirect("/winners");
    }
}
