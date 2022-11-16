package example.controllers;

import example.model.Message;
import example.service.TextService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexController extends HttpServlet {
    TextService textService = new TextService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> messageList = textService.getALlMessages();
        req.setAttribute("messages", messageList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/example/index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        textService.newText(text);
        resp.sendRedirect("/index");
    }
}