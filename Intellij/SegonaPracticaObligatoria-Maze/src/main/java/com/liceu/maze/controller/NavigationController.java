package com.liceu.maze.controller;

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

@WebServlet("/nav")
public class NavigationController extends HttpServlet {
    MazeService mazeService = new MazeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        MazeGame game = (MazeGame) session.getAttribute("game");
        String dir = req.getParameter("dir");
        boolean gameWon = (boolean) session.getAttribute("gameWon");

        if (dir != null && !gameWon) {
            game = mazeService.moveTo(dir, game);
        }

        if (game.getPlayer().getCurrentRoom().isTarget()) {
            session.setAttribute("gameWon", true);
            long startTime = (long) session.getAttribute("startTime");
            long timeExpent = new Date().getTime() - startTime;

            session.removeAttribute("startTime");
            session.setAttribute("timeExpent", timeExpent);
        }

        String myJson = mazeService.getJsonInfo(game);

        req.setAttribute("myjson", myJson);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/navigation.jsp");
        dispatcher.forward(req, resp);
    }
}
