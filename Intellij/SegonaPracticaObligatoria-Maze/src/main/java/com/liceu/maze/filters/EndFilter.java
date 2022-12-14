package com.liceu.maze.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/endform"})
public class EndFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();

        if (session.getAttribute("game") == null) {
            res.sendRedirect("/start");
            return;
        }

        boolean gameWon = (boolean) session.getAttribute("gameWon");

        if (!gameWon) {
            res.sendRedirect("/nav");
            return;
        }

        chain.doFilter(req, res);
    }
}
