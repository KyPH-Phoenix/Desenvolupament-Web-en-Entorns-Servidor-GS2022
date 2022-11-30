package com.liceu.maze.DAO;

import com.liceu.maze.model.Winner;

import java.util.List;

public interface WinnerDao {
    void addWinner(Winner winner);
    List<Winner> getWinners();
}
