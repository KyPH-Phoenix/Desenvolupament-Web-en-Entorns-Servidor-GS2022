package com.liceu.geom.service;

import com.liceu.geom.DAO.FigureDao;
import com.liceu.geom.DAO.FigureDaoListImpl;
import com.liceu.geom.model.Figure;

import java.util.List;
import java.util.stream.Collectors;

public class FigureService {
    FigureDao figureDao = new FigureDaoListImpl();
    static int lastId = 1;

    public void createFigure(int userId, String figureName, int xCord, int yCord, int size, String shape, String color) {
        Figure figure = new Figure();

        figure.setUser(new UserService().getUserById(userId));
        figure.setName(figureName);
        figure.setColor(color);
        figure.setShape(shape);
        figure.setSize(size);
        figure.setxCord(xCord);
        figure.setyCord(yCord);

        figure.setFigureId(lastId);
        lastId++;

        figureDao.addFigure(figure);
    }

    public List<Figure> getAllFigures() {
        return figureDao.getAllFigures();
    }

    public List<Figure> getAllFiguresFromUser(int userId) {
        List<Figure> figureList = figureDao.getAllFigures();

        return figureList
                .stream()
                .filter(figure -> figure.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    public Figure getFigure(int figureId) {
        List<Figure> figureList = figureDao.getAllFigures();

        return figureList
                .stream()
                .filter(figure -> figure.getFigureId() == figureId)
                .findFirst()
                .orElse(null);
    }

    public void deleteFigure(Figure figure) {
        figureDao.deleteFigure(figure);
    }
}
