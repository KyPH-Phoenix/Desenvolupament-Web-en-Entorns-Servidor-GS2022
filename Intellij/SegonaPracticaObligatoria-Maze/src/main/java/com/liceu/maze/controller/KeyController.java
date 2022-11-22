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

@WebServlet("/getkey")
public class KeyController extends HttpServlet {
    MazeService mazeService = new MazeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        MazeGame game = (MazeGame) session.getAttribute("game");

        try {
            game = mazeService.getKey(game);
        } catch (NonexistentKeyException e) {
            resp.setStatus(403);
            req.setAttribute("message", "La clau no existeix. Deixa de fer trampes.");
            resp.setHeader("Refresh", "3; URL=/nav");

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/error.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        session.setAttribute("game", game);

        resp.sendRedirect("/nav");
    }
}
