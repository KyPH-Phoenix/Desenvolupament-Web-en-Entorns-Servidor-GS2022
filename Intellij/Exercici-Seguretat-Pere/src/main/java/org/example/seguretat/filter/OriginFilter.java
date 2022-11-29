package org.example.seguretat.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {""})
public class OriginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String origin = req.getHeader("Origin");
        System.out.println(origin);

        if (origin != null) {
            if (!origin.equals("http://localhost:8080")) {
                System.out.println("man hackeado");
                resp.setStatus(401);
                PrintWriter pw = resp.getWriter();
                pw.println("ERROR");
                return;
            }
        }

        chain.doFilter(req, resp);
    }
}
