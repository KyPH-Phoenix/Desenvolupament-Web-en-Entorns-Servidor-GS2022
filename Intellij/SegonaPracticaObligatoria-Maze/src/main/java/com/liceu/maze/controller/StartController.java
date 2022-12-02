package com.liceu.maze.controller;

import com.liceu.maze.model.Maze;
import com.liceu.maze.service.MazeGame;
import com.liceu.maze.service.MazeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/start")
public class StartController extends HttpServlet {
    MazeService mazeService = new MazeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Maze> mazes = mazeService.getMaps();
        req.setAttribute("maps", mazes);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/start.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int mapId = Integer.parseInt(req.getParameter("mapid"));
        MazeGame mazeGame = mazeService.newGame(mapId);

        Date start = new Date();

        session.setAttribute("game", mazeGame);
        session.setAttribute("gameWon", false);
        session.setAttribute("mapId", mapId);
        session.setAttribute("startTime", start.getTime());

        resp.sendRedirect("/nav");
    }
}
