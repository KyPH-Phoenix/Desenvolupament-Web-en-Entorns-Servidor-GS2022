package org.example.seguretat.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/target2")
public class Target2Controller extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dins options");
        addCORSHeaders(resp);
    }

    private void addCORSHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8090");
        resp.addHeader("Access-Control-Allow-Credentials", "GET, POST, OPTIONS");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Allow-Headers", "Content-type");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Dins post");
        addCORSHeaders(resp);
        resp.getWriter().println("OK");
    }
}
