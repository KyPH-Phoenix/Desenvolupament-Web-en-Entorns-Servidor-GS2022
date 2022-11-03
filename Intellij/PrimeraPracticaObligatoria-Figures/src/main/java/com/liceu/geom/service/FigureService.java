package com.liceu.geom.service;

import com.liceu.geom.DAO.FigureDao;
import com.liceu.geom.DAO.FigureDaoListImpl;
import com.liceu.geom.model.Figure;

import java.util.List;

public class FigureService {
    FigureDao figureDao = new FigureDaoListImpl();

    public void createFigure(int userId, String figureName, int xCord, int yCord, int size, String shape, String color) {
        figureDao.addFigure(userId, figureName, xCord, yCord, size, shape, color);
    }

    public List<Figure> getAllFigures() {
        return figureDao.getAllFigures();
    }

    public List<Figure> getAllFiguresFromUser(int userId) {
        return figureDao.getAllFiguresFromUser(userId);
    }
}
