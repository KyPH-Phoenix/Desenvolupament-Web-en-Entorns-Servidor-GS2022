package com.liceu.geom.DAO;

import com.liceu.geom.model.Figure;

import java.util.ArrayList;
import java.util.List;

public interface FigureDao {

    void addFigure(int userId, String figureName, int xCord, int yCord, int size, String shape, String color);
    List<Figure> getAllFigures();
    List<Figure> getAllFiguresFromUser(int userId);
}
