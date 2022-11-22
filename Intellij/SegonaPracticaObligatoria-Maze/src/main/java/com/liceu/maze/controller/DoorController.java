package com.liceu.maze.controller;

import com.liceu.maze.exceptions.AlreadyOpenDoorException;
import com.liceu.maze.exceptions.NotDoorException;
import com.liceu.maze.exceptions.NotValidDirectionException;
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

@WebServlet("/open")
public class DoorController extends HttpServlet {
    MazeService mazeService = new MazeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MazeGame game = (MazeGame) session.getAttribute("game");
        String dir = req.getParameter("dir");

        if (dir != null) {
            try {
                game = mazeService.openDoor(dir, game);
            } catch (NotDoorException e) {
                resp.setStatus(403);
                req.setAttribute("message", "No pots obrir una paret.");
                resp.setHeader("Refresh", "3; URL=/nav");

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/error.jsp");
                dispatcher.forward(req, resp);
                return;
            } catch (AlreadyOpenDoorException e) {
                resp.setStatus(403);
                req.setAttribute("message", "La porta ja està oberta. No la pots tornar a obrir.");
                resp.setHeader("Refresh", "3; URL=/nav");

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/error.jsp");
                dispatcher.forward(req, resp);
                return;
            } catch (NotValidDirectionException e) {
                resp.setStatus(403);
                req.setAttribute("message", "S'ha modificat el valor de la direcció. El valor no és vàlid");
                resp.setHeader("Refresh", "3; URL=/nav");

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/error.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            resp.setStatus(403);
            req.setAttribute("message", "S'ha modificat el valor de la direcció. El valor no és vàlid");
            resp.setHeader("Refresh", "3; URL=/nav");

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }

        session.setAttribute("game", game);

        resp.sendRedirect("/nav");
    }
}
