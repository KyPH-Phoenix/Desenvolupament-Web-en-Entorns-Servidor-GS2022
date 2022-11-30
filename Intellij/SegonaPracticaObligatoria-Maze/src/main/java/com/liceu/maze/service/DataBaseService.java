package com.liceu.maze.service;

import com.liceu.maze.DAO.WinnerDao;
import com.liceu.maze.DAO.WinnerDaoMysql;
import com.liceu.maze.model.Winner;
import com.liceu.maze.util.Util;

import java.util.List;

public class DataBaseService {
    WinnerDao winnerDao = new WinnerDaoMysql();

    public void registerWinner(String userName, String mapName, long timeSpent) {
        System.out.println("Dentro del service");
        Winner winner = new Winner();

        winner.setUserName(userName);
        winner.setMapName(mapName);
        winner.setElapsedTime(timeSpent);

        winnerDao.addWinner(winner);
    }

    public List<Winner> getWinners() {
        List<Winner> winners = winnerDao.getWinners();

        winners.forEach(winner -> {
                    String stringTime = Util.timeToString(winner.getElapsedTime());
                    winner.setStringTime(stringTime);
                });

        return winners;
    }
}
