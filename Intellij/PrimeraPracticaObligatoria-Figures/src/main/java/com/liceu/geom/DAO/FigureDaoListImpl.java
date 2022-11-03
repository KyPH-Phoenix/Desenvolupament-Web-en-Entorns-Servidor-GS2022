package com.liceu.geom.DAO;

import com.liceu.geom.model.Figure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FigureDaoListImpl implements FigureDao {
    static List<Figure> figureList = new ArrayList<>();
    static int lastId = 1;

    @Override
    public void addFigure(int userId, String figureName, int xCord, int yCord, int size, String shape, String color) {
        Figure figure = new Figure();

        figure.setUserId(userId);
        figure.setName(figureName);
        figure.setColor(color);
        figure.setShape(shape);
        figure.setSize(size);
        figure.setxCord(xCord);
        figure.setyCord(yCord);

        figure.setFigureId(lastId);
        lastId++;

        figureList.add(figure);
    }

    @Override
    public List<Figure> getAllFigures() {
        return figureList;
    }

    @Override
    public List<Figure> getAllFiguresFromUser(int userId) {
        return figureList
                .stream()
                .filter(figure -> figure.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
