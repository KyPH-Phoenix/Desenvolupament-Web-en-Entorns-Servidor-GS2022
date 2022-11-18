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

@WebServlet("/getcoin")
public class CoinController extends HttpServlet {
    MazeService mazeService = new MazeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        MazeGame game = (MazeGame) session.getAttribute("game");

        game = mazeService.getCoin(game.getId());

        session.setAttribute("game", game);

        resp.sendRedirect("/nav");
    }
}
