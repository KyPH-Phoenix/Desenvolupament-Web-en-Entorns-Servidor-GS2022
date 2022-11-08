package com.liceu.geom.DAO;

import com.liceu.geom.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class FigureDaoListImpl implements FigureDao {
    static List<Figure> figureList = new ArrayList<>();
    static int lastId = 1;

    @Override
    public synchronized void addFigure(Figure figure) {
        figure.setFigureId(lastId);
        lastId++;

        figureList.add(figure);
    }

    @Override
    public List<Figure> getAllFigures() {
        return figureList;
    }

    @Override
    public synchronized void deleteFigure(Figure figure) {
        figureList.removeIf(
                originalFigure -> originalFigure.getFigureId() == figure.getFigureId()
        );
    }
}
