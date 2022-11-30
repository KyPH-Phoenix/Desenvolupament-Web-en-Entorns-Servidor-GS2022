package com.liceu.maze.controller;

import com.liceu.maze.exceptions.NonexistentKeyException;
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

@WebServlet("/reset")
public class ResetController extends HttpServlet {
    MazeService mazeService = new MazeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int mapId = (int) session.getAttribute("mapId");

        MazeGame game = (MazeGame) session.getAttribute("game");
        game = mazeService.resetGame(mapId, game);

        long startTime = new Date().getTime();

        session.setAttribute("game", game);
        session.setAttribute("gameWon", false);
        session.setAttribute("startTime", startTime);

        resp.sendRedirect("/nav");
    }
}
