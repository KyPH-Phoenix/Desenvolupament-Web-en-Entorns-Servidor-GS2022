package com.liceu.maze.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@WebServlet("/endform")
public class EndController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        long startTime = (long) session.getAttribute("startTime");
        long timeExpent = new Date().getTime() - startTime;

        String totalTime = getTotalTime(timeExpent);
        req.setAttribute("time", totalTime);

        session.removeAttribute("startTime");
        session.setAttribute("totalTime", timeExpent);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/endform.jsp");
        dispatcher.forward(req, resp);
    }

    private String getTotalTime(long time) {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(hours);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(minutes)
                - TimeUnit.HOURS.toSeconds(hours);

        return String.format("%dh, %dm, %ds", hours, minutes, seconds);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
