package com.liceu.maze.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ExempleService {
    public String getJsonInfo() {
        JSONObject root = new JSONObject();
        JSONObject walls = new JSONObject();

        walls.put("N", "wall");
        walls.put("S", "door");
        walls.put("W", "wall");
        walls.put("E", "door");
        root.put("walls", walls);

        JSONObject player = new JSONObject();
        JSONArray array = new JSONArray();

        array.add("Clau1");
        array.add("Moneda");
        array.add("moneda");

        player.put("inventory", array);

        root.put("player", player);

        return root.toJSONString();
    }
}
