package com.liceu.maze.controller;

import com.liceu.maze.service.DataBaseService;

import com.liceu.maze.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/endform")
public class EndController extends HttpServlet {
    DataBaseService dataBaseService = new DataBaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        long timeExpent = (long) session.getAttribute("timeExpent");

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
        HttpSession session = req.getSession();

        String userName = req.getParameter("userName");
        String mapName = "Mapa" + (int) session.getAttribute("mapId");
        long timeSpent = (long) session.getAttribute("timeExpent");

        System.out.println(userName);
        System.out.println(mapName);
        System.out.println(timeSpent);

        System.out.println("*Se inserta el user*");
        dataBaseService.registerWinner(userName, mapName, timeSpent);

        session.removeAttribute("userName");
        session.removeAttribute("mapId");
        session.removeAttribute("timeExpent");
        session.removeAttribute("gameWon");

        resp.sendRedirect("/winners");
    }
}
