package com.liceu.geom.DAO;

import com.liceu.geom.model.Figure;

import java.util.ArrayList;
import java.util.List;

public interface FigureDao {
    void addFigure(Figure figure);
    List<Figure> getAllFigures();
    void deleteFigure(Figure figure);
}
