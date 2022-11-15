package com.liceu.maze.controller;

import com.liceu.maze.model.Maze;
import com.liceu.maze.service.MazeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        int mapId = Integer.parseInt(req.getParameter("mapid"));



        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/start.jsp");
        dispatcher.forward(req, resp);
    }
}
