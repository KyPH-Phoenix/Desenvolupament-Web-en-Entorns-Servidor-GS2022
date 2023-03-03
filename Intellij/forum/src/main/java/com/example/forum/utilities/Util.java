package com.example.forum.utilities;

import com.example.forum.model.Category;
import com.example.forum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static Map<String, Object> buildUserMap(User u, List<Category> categories) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("role", u.getRole());
        userMap.put("_id", u.getId());
        userMap.put("email", u.getEmail());
        userMap.put("name", u.getName());
        userMap.put("__v", 0);
        userMap.put("avatarurl", "");

        Map<String, Object> permissionMap = new HashMap<>();
        permissionMap.put("root", new String[]{"categories:write","categories:delete"});
        permissionMap.put("categories", categories);

        userMap.put("permissions", permissionMap);

        return userMap;
    }

    public static Map<String, Object> buildCategoryMap(Category c) {
        Map<String, Object> map = new HashMap<>();

        map.put("color", c.getColor());
        map.put("description", c.getDescription());
        map.put("moderators", new ArrayList<>());
        map.put("slug", c.getSlug());
        map.put("title", c.getTitle());
        map.put("__v", 0);
        map.put("_id", c.getId());

        return map;
    }

    public static String generateRandomColor() {
        int value = (int) (Math.random() * 360);

        return "hsl(" + value + ", 50%, 50%)";
    }
}
