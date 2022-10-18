package com.liceu.DemoServlets.controllers;

import com.liceu.DemoServlets.model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/mvc")
public class MVC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/view1.jsp");
        req.setAttribute("nom", "Hugo");
        req.setAttribute("profesio", "Inutil");
        req.setAttribute("array", new int[]{1, 2, 3, 4});

        Person person = new Person();
        person.setName("Hugo");
        person.setBirthYear(2002);

        req.setAttribute("person", person);

        int temp = 45;

        req.setAttribute("temp", temp);

        Map<String, Integer> map = new HashMap<>();
        map.put("Un", 1);
        map.put("Dos", 2);
        map.put("Tres", 3);
        map.put("Quatre", 4);

        req.setAttribute("mapa", map);

        String[][] horario = new String[3][6];

        horario[0][0] = "Hores";
        horario[0][1] = "Dilluns";
        horario[0][2] = "Dimarts";
        horario[0][3] = "Dimecres";
        horario[0][4] = "Dijous";
        horario[0][5] = "Divendres";

        horario[1][0] = "15:00 - 15:55";
        horario[1][1] = "DWEC";
        horario[1][2] = "Desplegament";
        horario[1][3] = "EIE";
        horario[1][4] = "DWES";
        horario[1][5] = "Desplegament";

        horario[2][0] = "15:55 - 16:50";
        horario[2][1] = "DWES";
        horario[2][2] = "Diseny";
        horario[2][3] = "DWEC";
        horario[2][4] = "DWES";
        horario[2][5] = "DWEC";

        req.setAttribute("horari", horario);
        dispatcher.forward(req, resp);
    }
}
