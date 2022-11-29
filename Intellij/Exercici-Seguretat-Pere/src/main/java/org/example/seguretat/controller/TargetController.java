package org.example.seguretat.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/target")
public class TargetController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Estamos dentro");
        PrintWriter writer = resp.getWriter();

        int n1 = Integer.parseInt(req.getParameter("n1"));
        int n2 = Integer.parseInt(req.getParameter("n2"));

        writer.printf("%d + %d = %d", n1, n2, n1 + n2);

        System.out.println("Estamos dentro x2");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Estamos dentro post");
        PrintWriter writer = resp.getWriter();

        int n1 = Integer.parseInt(req.getParameter("n1"));
        int n2 = Integer.parseInt(req.getParameter("n2"));

        writer.printf("%d + %d = %d", n1, n2, n1 + n2);
        System.out.println("Estamos dentro x2 post");
    }
}
