package com.liceu.maze.controller;

import com.liceu.maze.model.Winner;
import com.liceu.maze.service.DataBaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/winners")
public class WinnerController extends HttpServlet {
    DataBaseService dataBaseService = new DataBaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Winner> winners = dataBaseService.getWinners();

        req.setAttribute("winners", winners);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/winners.jsp");
        dispatcher.forward(req, resp);
    }
}
