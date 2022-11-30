package com.liceu.maze.controller;

import com.liceu.maze.service.MazeGame;
import com.liceu.maze.service.MazeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/exit")
public class ExitControler extends HttpServlet {
    MazeService mazeService = new MazeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        MazeGame mazeGame = (MazeGame) session.getAttribute("game");
        mazeService.removeGame(mazeGame);

        session.removeAttribute("game");
        session.removeAttribute("gameWon");
        session.removeAttribute("mapId");
        session.removeAttribute("startTime");

        resp.sendRedirect("/start");
    }
}
